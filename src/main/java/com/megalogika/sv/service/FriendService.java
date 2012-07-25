package com.megalogika.sv.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.megalogika.sv.model.Donator;
import com.megalogika.sv.model.Partner;

@Service("friendService")
public class FriendService {
	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Partner> getListOfPartners() {
		return em.createQuery("select f from Partner f order by f.id desc").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Donator> getListOfDonators() {
		return em.createQuery("select f from Donator f order by f.id desc").getResultList();
	}
	
	public Partner loadPartner(long id) {
		return em.find(Partner.class, id);
	}
	
	public Donator loadDonator(long id) {
		return em.find(Donator.class, id);
	}

	@Transactional
	public Partner add(Partner partner) {
		return em.merge(partner);
	}
	
	@Transactional 
	public Donator add(Donator donator) {
		return em.merge(donator);
	}
	
	@Transactional
	public void delete(Partner partner) {
		em.remove(partner);
	}

	@Transactional
	public void delete(Donator donator) {
		em.remove(donator);
	}
}
