package com.megalogika.sv.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Report extends TimedEvent {

	private Product product;
	private String enteredByIp;	
	
	public Report() {
		super();
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(cascade={CascadeType.MERGE})
	public Product getProduct() {
		return product;
	}
	
	public Report(User user) {
		this();
		setActor(user);
	}	
	
	public Report(Product p, User user) {
		this(user);
		setProduct(p);
		if (user != null) setEnteredByIp(user.getUserAddres());
	}
	
	public String getEnteredByIp() {
		return enteredByIp;
	}

	public void setEnteredByIp(String enteredByIp) {
		this.enteredByIp = enteredByIp;
	}	

	@Override
	public String toString() {
		return "REPORT[" + product + " by " + getActor()+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = ((getActor() == null) ? 0 : getActor().hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Report other = (Report) obj;
		if (getActor() == null) {
			if (other.getActor() != null)
				return false;
		} else if (getActor().getId() != other.getActor().getId())
			return false;		
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}	
	
	
}
