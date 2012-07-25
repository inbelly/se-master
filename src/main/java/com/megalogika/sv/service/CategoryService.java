package com.megalogika.sv.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.megalogika.sv.model.ProductCategory;

@Service("categoryService")
@Repository
public class CategoryService {
	Logger logger = Logger.getLogger(CategoryService.class);

	public final static Long CATEGORY_MISC = 16L;
	
	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ProductCategory> getList() {
		return em.createQuery("select c from ProductCategory c order by c.index asc").getResultList();
	}

	public ProductCategory getProductCategory(long id) {
		ProductCategory ret = em.find(ProductCategory.class, id);
		
		return ret;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public ProductCategory getProductCategory(String name) {
		@SuppressWarnings("unchecked")
		List<ProductCategory> list = em.createQuery("select c from ProductCategory c where name = :name").setParameter("name", name).getResultList();
		
		ProductCategory ret = null;
		if (null != list && list.size() > 0) {
			ret = list.get(0);
		}
		
		return ret;
	}

}
