package com.megalogika.sv.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.megalogika.sv.model.Product;
import com.megalogika.sv.model.ShoppingBasket;
import com.megalogika.sv.model.User;

@Service("basketService")
@Repository
public class BasketService {

	protected transient Logger logger = Logger.getLogger(BasketService.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void putProductToBasket(User user, Product product) {
		
//		ShoppingBasket basket = user.getShoppingBasket();
//		
//		if (null == basket) {
//			basket = new ShoppingBasket();
//			em.persist(basket);
//			user.addShoppingBasket(basket);
//		}
//		
//		if (! user.hasInBasket(product)) {
//			basket.add(product);
//		}
		
	}

	@Transactional
	public void removeProductFromBasket(User user, Product product) {
		
//		ShoppingBasket basket = user.getShoppingBasket();
//		if (null != basket) {
//			basket.remove(product);
//			if (0 == basket.size()) {
//				user.removeShoppingBasket();
//				em.merge(user);
//				em.remove(em.getReference(ShoppingBasket.class, basket.getId()));
//			}
//		}	
	}
	
}
