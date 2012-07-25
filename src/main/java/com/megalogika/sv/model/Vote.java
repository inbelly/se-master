package com.megalogika.sv.model;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public class Vote extends TimedEvent {

	private int vote;

	public void setVote(int vote) {
		this.vote = vote;
	}

	@Basic
	public int getVote() {
		return vote;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + vote + getActor().hashCode();
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
		Vote other = (Vote) obj;
		if (vote != other.vote)
			return false;
		return null != this.getActor() && this.getActor().equals(other.getActor());
	}
	
	
}
