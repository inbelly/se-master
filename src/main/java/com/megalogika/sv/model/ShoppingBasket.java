package com.megalogika.sv.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

@Entity
public class ShoppingBasket implements Serializable {
	private static final long serialVersionUID = -1086114948591672715L;
	
	private long id;
	private User owner;
	private List<Product> products;

	public ShoppingBasket() {
		products = new ArrayList<Product>();
	}

	public ShoppingBasket(User user) {
		this();
		this.owner = user;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public long getId() {
		return id;
	}	
	
	public void setOwner(User owner) {
		this.owner = owner;
	}

	@OneToOne(targetEntity=User.class)
	public User getOwner() {
		return owner;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@ManyToMany(fetch=FetchType.EAGER, targetEntity=Product.class, mappedBy="baskets")
	@JoinTable(name="product_shoppingbasket")
	public List<Product> getProducts() {
		return products;
	}

	public boolean contains(Product p) {
		Assert.notNull(products);
		
		return products.contains(p);
	}

	public void add(Product product) {
		Assert.notNull(products);
		Assert.notNull(product);
		
		product.getBaskets().add(this);		
		products.add(product);
	}

	protected transient Logger logger = Logger.getLogger(ShoppingBasket.class);
	
	public void remove(Product product) {
		Assert.notNull(products);
		Assert.notNull(product);
		
		logger.debug("FROM BASKET " + this + " REMOVE PRODUCT: " + product);
		
		logger.debug("IN THESE BASKETS: "  + product.getBaskets());
		product.getBaskets().remove(this);
		products.remove(product);
		logger.debug("IN THESE BASKETS: "  + product.getBaskets());
		
	}

	public int size() {
		Assert.notNull(products);
		
		return products.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingBasket other = (ShoppingBasket) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
