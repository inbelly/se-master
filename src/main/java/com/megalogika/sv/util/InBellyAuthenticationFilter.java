package com.megalogika.sv.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

import com.megalogika.sv.model.User;
import com.megalogika.sv.service.UserService;

public class InBellyAuthenticationFilter extends AuthenticationProcessingFilter {

	@Autowired
	private
	UserService userService;
	
	@Autowired
	private UserDetailsService userDetailsService;	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request) throws AuthenticationException {
        
		String fbEmail = request.getParameter("fbemail");
		
        if (! StringUtils.hasText(fbEmail)) {
        	logger.debug(" NO fbEmail proceeding to super");
        	return super.attemptAuthentication(request);	
        } else {
        	UsernamePasswordAuthenticationToken auth = null;
        	
        	UserDetails userDetails = null;
        	try {
        		userDetails = userDetailsService.loadUserByUsername(fbEmail);
        	} catch (UsernameNotFoundException e) {
        		logger.debug("NERADOM!!!");
        		userDetails = null;
        	}
        	
        	if (null != userDetails) {
        		auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        	} else { // ikurti toki vartotoja ir ji ataduoti
        		String fbName = request.getParameter("fbname");
        		
        		if (!"undefined".equals(fbEmail)) {
	        		
	        		User user = new User();
	        		user.setUserAddres(request.getRemoteAddr() + " ("
	    					+ request.getRemoteHost() + ")");
	        		user.setEmail(fbEmail);
	        		user.setNick(fbName);
	        		userService.save(user);
	        		
	        		auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        		} else {
        			auth = null;
        		}
        		
        	}
        	
        	return auth;
        }
		
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}
}
