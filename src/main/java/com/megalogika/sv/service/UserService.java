package com.megalogika.sv.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ml.generator.PronounceableGenerator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationManager;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.security.providers.rememberme.RememberMeAuthenticationToken;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsManager;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.megalogika.sv.model.User;

@Service("userService")
@Repository

public class UserService  {
	protected transient Logger logger = Logger.getLogger(UserService.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private EmailActions emailActions;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEm() {
		return em;
	}

	public boolean emailExists(String email) {
		try {
			return null != userDetailsService.loadUserByUsername(email);
		} catch (UsernameNotFoundException e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean nickExists(String nick) {
		Assert.notNull(nick);

		List<User> l = em.createQuery("select u from User u where u.nick = :nick").setParameter("nick", nick.trim()).getResultList();
		return !CollectionUtils.isEmpty(l);
	}
	
	@Transactional
	public User save(User u) {
		u.setPassword(new Md5PasswordEncoder().encodePassword(u.getPassword(), null));
		User ret = em.merge(u);
		return ret;
	}
	
	public boolean resetPassword(String email) {
		Assert.notNull(email, "Negaliu atnaujinti slaptažodžio, kai nenurodytas e-paštas.");
		
		User u = (User) em.createQuery("select u from User u where LOWER(u.email) = LOWER(:email)").setParameter("email", email).getSingleResult();
		if (null == u) {
			logger.warn("Null user for email '" + email + "'");
			return false;
		}
		
		String newPassword = new PronounceableGenerator().generate(8);
		u.setPassword(newPassword);
		save(u);
		
		emailActions.sendNewPassword(u, newPassword);
		
		return true;
	}

	public void setEmailActions(EmailActions emailActions) {
		this.emailActions = emailActions;
	}

	public EmailActions getEmailActions() {
		return emailActions;
	}
	
	@SuppressWarnings("unchecked")
	public User getCurrentUser() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		if (null == auth) {
			logger.error("AUTH IS NULL????");
			return null;
		}
		if (auth.getPrincipal() instanceof User) {
			return (User) auth.getPrincipal();
		} else {
			String principal = (String) auth.getPrincipal();
			if ("roleAnonymous".equals(principal)) {
				logger.debug("user not logged on!");
				return null;
			} 
			
			try {
				return loadByUserId((String) auth.getPrincipal());
			} catch (Exception e) {
				logger.warn(e, e);
				return null;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public User loadByUserId(String userid) {
		List<User> l = em.createQuery("select u from User u where LOWER(u.id) = LOWER(:userid)").setParameter("userid", userid).getResultList();
		if (CollectionUtils.isEmpty(l)) {
			throw new UsernameNotFoundException("Nėra vartotojo, kurio id '" + userid + "'");
		}
		return (User) l.get(0);
	}
	
	public void persist(User user) {
		em.persist(user);
	}
	
	public User merge(User user) {
		return em.merge(user); 
	}
	
	public User refresh(User user) {
		em.refresh(user);
		return user;
	}
	
	public boolean forceLogin(User u) {
		if (null == u) {
			return false;
		} else {
			try {
				RememberMeAuthenticationToken upat = new RememberMeAuthenticationToken("inbelly", u, u.getAuthorities());
				Authentication auth = authenticationManager.authenticate(upat);
				SecurityContext ctx = SecurityContextHolder.getContext();
				ctx.setAuthentication(auth);
			} catch (Exception e) {
				logger.error(e);
				return false;
			}
				return true;
		}
	}
}
