package com.megalogika.sv.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

@Service("userDetailsService")
public class SvUserDetailsService implements UserDetailsService {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		Assert.notNull(username);
		
		List<UserDetails> l = em.createQuery("select u from User u where LOWER(u.email) = LOWER(:email)").setParameter("email", username.trim()).getResultList();
		if (CollectionUtils.isEmpty(l)) {
			throw new UsernameNotFoundException("Nėra vartotojo, kurio el. pašto adresas '" + username + "'");
		}
		return (UserDetails) l.get(0);
	}

}
