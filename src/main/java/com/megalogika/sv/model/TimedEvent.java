package com.megalogika.sv.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@MappedSuperclass
public abstract class TimedEvent {

	private long id;
	protected Date eventTime;
	private User actor;

	public TimedEvent() {
		eventTime = Calendar.getInstance().getTime();
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actor == null) ? 0 : actor.hashCode());
		result = prime * result + ((eventTime == null) ? 0 : eventTime.hashCode());
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
		TimedEvent other = (TimedEvent) obj;
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
		if (id != other.id)
			return false;
		return true;
	}

	
}
