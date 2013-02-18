package com.megalogika.sv.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Confirmation implements Serializable {
	
	/**
	 * generated serialVersionUID
	 */
	private static final long serialVersionUID = 7102806200355464590L;

	@Transient
	protected transient Logger logger = Logger.getLogger(Confirmation.class);
	
	private long id;
	protected Date eventTime;
	private User actor;
	private Product product;
	private String enteredByIp;
	
	public Confirmation() {
		super();
	}
	
	public Confirmation(User user) {
		this();
		eventTime = Calendar.getInstance().getTime();
		setActor(user);
	}	
	
	public Confirmation(Product p, User user) {
		this(user);
		setProduct(p);
		if (user != null) setEnteredByIp(user.getUserAddres());
	}
	
	public void setId(long id) {
		this.id = id;
	}	
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public long getId() {
		return id;
	}

	public void setEventTime(Date changeTime) {
		this.eventTime = changeTime;
	}

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEventTime() {
		return eventTime;
	}

	public void setActor(User user) {
		this.actor = user;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade={CascadeType.REFRESH})
	public User getActor() {
		return actor;
	}	

	@Override
	public String toString() {
		return "CONFIRMATION[" + product + " by " + getActor()+"]";
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(cascade={CascadeType.MERGE})
	public Product getProduct() {
		return product;
	}
	
	public String getEnteredByIp() {
		return enteredByIp;
	}

	public void setEnteredByIp(String enteredByIp) {
		this.enteredByIp = enteredByIp;
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
		Confirmation other = (Confirmation) obj;
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
		logger.debug("EQUAL!");
		return true;
	}	
	
}
