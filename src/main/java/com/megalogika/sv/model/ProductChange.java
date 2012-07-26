package com.megalogika.sv.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProductChange extends TimedEvent {
	protected transient Logger logger = Logger.getLogger(ProductChange.class);
	
	private Product product;
	
	public ProductChange() {
		super();
	}
	
	public ProductChange(User user) {
		this();
		setActor(user);
	}
	
	public ProductChange(Product p, User user) {
		this(user);
		setProduct(p);
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(cascade={CascadeType.MERGE})
	public Product getProduct() {
		return product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getActor() == null) ? 0 : getActor().hashCode());		
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		ProductChange other = (ProductChange) obj;
		if (getActor() == null) {
			if (other.getActor() != null)
				return false;
		} else if (!getActor().equals(other.getActor()))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}	
	
}
