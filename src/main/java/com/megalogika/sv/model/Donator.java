package com.megalogika.sv.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Donator extends Friend implements Serializable {
	private static final long serialVersionUID = 2801089298920115582L;

	@Basic
	private String amount;
	@Basic
	private String currency;
	@Temporal(TemporalType.DATE)
	private Date donationDate;

	public Donator() {
		super();
		donationDate = Calendar.getInstance().getTime();
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmount() {
		return amount;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrency() {
		return currency;
	}

	public void setDonationDate(Date donationDate) {
		this.donationDate = donationDate;
	}

	public Date getDonationDate() {
		return donationDate;
	}
}
