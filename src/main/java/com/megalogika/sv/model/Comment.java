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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.engine.CascadeStyle;

@Entity
public class Comment  extends TimedEvent implements Serializable {
	private static final long serialVersionUID = -5826843171572894636L;

	String name;
	String email;
	String website;

	String body;
	
	private Product product;
	
	private boolean processed;
	
	public Comment() {
		super();
	}
	
	public Comment(User user) {
		this();
		setActor(user);
	}
	
	public Comment(Product p, User user) {
		this(user);
		setProduct(p);
	}	
	
	@Override
	public String toString() {
		return "COMMENT["+getActor()+"@"+getEventTime()+" on " + getProduct() + "] ";
	}
	
	@Basic
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Basic
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Basic
	@Lob
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public boolean isProcessed() {
		return processed;
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
		result = prime * result + (int) (getId() ^ (getId() >>> 32));
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
		Comment other = (Comment) obj;
		if (getId() != other.getId())
			return false;
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Comment cmt = new Comment();
		cmt.setActor(this.getActor());
		cmt.setEventTime(this.getEventTime());
		cmt.setName(this.getName());
		cmt.setBody(this.getBody());
		cmt.setEmail(this.getEmail());
		cmt.setProduct(this.getProduct());
		cmt.setWebsite(this.getWebsite());
		
		return cmt;
	}
}
