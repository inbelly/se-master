package com.megalogika.sv.model;

import java.io.Serializable;

import javax.persistence.Basic;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//import org.hibernate.annmotations.Cascade;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@UniqueConstraint(columnNames = { "email" })
public class User implements Serializable, UserDetails {
	private static final long serialVersionUID = -3645402537444611893L;
	
	protected transient org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	long id;
	String nick;
	private String email;
	private String password;
	private String passwordVerify;
	private boolean aggree;
	private String role;
	
	@Transient
	private String userAddress;
	
	@Transient
	public String getUserAddres() {
		return userAddress;
	}

	@Transient
	public void setUserAddres(String userAddress) {
		this.userAddress = userAddress;
	}

	private String facebookId;
	//private ShoppingBasket shoppingBasket;

	public User() {
		setRole(ROLE_USER);
	}

	@Override
	public String toString() {
		return "User[" + id + ": " + nick + "@" + email + ":" + role + "]";
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(length = 512)
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length = 512)
	public String getEmail() {
		return email;
	}

	@Override
	@Transient
	public GrantedAuthority[] getAuthorities() {
		return new GrantedAuthority[] { new GrantedAuthorityImpl(getRole()) };
	}

	@Override
	@Basic
	public String getPassword() {
		return password;
	}

	@Override
	@Transient
	public String getUsername() {
		return email;
	}

	@Override
	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@Transient
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@Transient
	public boolean isEnabled() {
		return true;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Basic
	public String getRole() {
		return role;
	}

	public void setPasswordVerify(String passwordVerify) {
		this.passwordVerify = passwordVerify;
	}

	@Transient
	public String getPasswordVerify() {
		return passwordVerify;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nick == null) ? 0 : nick.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (nick == null) {
			if (other.nick != null) {
				return false;
			}
		} else if (!nick.equals(other.nick)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (role == null) {
			if (other.role != null) {
				return false;
			}
		} else if (!role.equals(other.role)) {
			return false;
		}
		return true;
	}

//	@OneToOne(fetch=FetchType.LAZY, mappedBy="owner", cascade={CascadeType.ALL})
//	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
//	public ShoppingBasket getShoppingBasket() {
//		return shoppingBasket;
//	}
//
//	public void setShoppingBasket(ShoppingBasket shoppingBasket) {
//		this.shoppingBasket = shoppingBasket;
//	}
//
//	public boolean hasInBasket(Product p) {
//		return null != getShoppingBasket() && getShoppingBasket().contains(p);
//	}
//
//	public void addShoppingBasket(ShoppingBasket basket) {
//		if (null != basket) {
//			basket.setOwner(this);
//		}
//		this.shoppingBasket = basket;
//		
//	}
//	
//	public void removeShoppingBasket() {
//		if (null != this.shoppingBasket) {
//			this.shoppingBasket.setOwner(null);
//		}
//		this.shoppingBasket = null;
//	}

	public void setAggree(boolean aggree) {
		this.aggree = aggree;
	}

	@Transient
	public boolean isAggree() {
		return aggree;
	}
	
	@Transient
	public boolean isAdmin() {
		return ROLE_ADMIN.equals(role);
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getFacebookId() {
		return facebookId;
	}
}
