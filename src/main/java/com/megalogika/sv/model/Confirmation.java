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
public class Confirmation /*extends TimedEvent*/ implements Serializable {
	private static final long serialVersionUID = -7428117547697707665L;
	
	@Transient
	protected transient Logger logger = Logger.getLogger(Confirmation.class);
	
	private Product product;
	private String enteredByIp;

   
	
	public Confirmation() {
		super();
		eventTime = Calendar.getInstance().getTime();
	}
	
	public Confirmation(User user) {
		this();
		setActor(user);
		eventTime = Calendar.getInstance().getTime();
	}	
	
	public Confirmation(Product p, User user) {
		this(user);
		setProduct(p);
		if (user != null) setEnteredByIp(user.getUserAddres());
		eventTime = Calendar.getInstance().getTime();
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
    

    /*FIXME: save-hack*/
	
    private long id;

    protected Date eventTime;

    private User actor;
    
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

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    public User getActor() {
    	return actor;
    }	
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((actor == null) ? 0 : actor.hashCode());
        result = prime * result
                + ((eventTime == null) ? 0 : eventTime.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
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
        Confirmation other = (Confirmation) obj;
        if (actor == null) {
            if (other.actor != null)
                return false;
        } else if (!actor.equals(other.actor))
            return false;
        if (eventTime == null) {
            if (other.eventTime != null)
                return false;
        } else if (!eventTime.equals(other.eventTime))
            return false;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        return true;
    }

    
    
	/**/
    
    
    
}
