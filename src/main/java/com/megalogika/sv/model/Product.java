package com.megalogika.sv.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.log4j.Logger;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.util.Assert;

import com.megalogika.sv.model.conversion.JsonFilterable;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product implements Serializable, JsonFilterable, Comparable {
	private static final long serialVersionUID = 6032201133972384748L;
	
	protected transient Logger logger = Logger.getLogger(Product.class);

	private Integer confirmationsRequired = 2;
	private Integer reportsRequired = 2;
	
	long id;
	String name;
	String company;
	String grouping;
	ProductCategory category;
	private String tags;
	List<E> conservants;
	String conservantsText;
	String conservantsParsed;
	private boolean conservantFree;
	private String calories;
	boolean approved = false;
	private boolean approvedContent = false;
	Date entryDate;
	private String enteredByIp;
	private boolean notify;
	private boolean gmo;
	String hazard;
	private User user;
	private List<Comment> comments;
	private List<ShoppingBasket> baskets;
	private long viewCount = 0;
	private long barcode;
	
	private Photo label;
	private Photo ingredients;
	
	private String labelPhoto;
	private String ingredientsPhoto;
	
	private List<ProductChange> changes = new ArrayList<ProductChange>();
	private List<Vote> votes = new ArrayList<Vote>();
	private long voteCount;
	private long voteTotal;
	
	private List<Confirmation> confirmations = new ArrayList<Confirmation>();
	private int confirmationCount = 0;
	
	private List<Report> reports = new ArrayList<Report>();

	public Product() {
		entryDate = Calendar.getInstance().getTime();
		user = new User();
		category = new ProductCategory();
		changes = new ArrayList<ProductChange>();
		votes = new ArrayList<Vote>();
		confirmations = new ArrayList<Confirmation>();
		confirmationCount = 0;
		reports = new ArrayList<Report>();
	}

	@Override
	public String toString() {
		return "Product["+name+"@"+id+"by:"+user+category + "]";
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Basic
	@Column(columnDefinition = "text")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//FIXME: save-hack
	//@ManyToMany(targetEntity = E.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@ManyToMany(targetEntity = E.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@BatchSize(size = 64)
	@OrderBy("category DESC")
	public List<E> getConservants() {
		return conservants;
	}

	public void setConservants(List<E> conservants) {
		this.conservants = conservants;
	}

	@Basic
	public boolean isApproved() {
		return confirmationCount > 1;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	@Basic
	@Column(length = 400)
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Basic
	public String getGrouping() {
		return grouping;
	}

	public void setGrouping(String grouping) {
		this.grouping = grouping;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Basic
	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * čia yra paslaptis, nes po to kai getHazard() paskaičiuoja mums hazard
	 * reikšmę ji yra įrašoma į DB ir paskui veikia įvairūs visokie selektai ir
	 * filtrai pagal hazard woo fucking doo --ve
         * 
         * atrodo kad tai paskaiciuoja pries issaugant metode Product.calculateHazard() --jg
	 * 
	 * @return
	 */
	@Basic
	public String getHazard() {
		return hazard;
	}

	public void setHazard(String s) {
		hazard = s;
	}

	public void calculateHazard() {
		if (null == getConservants() || getConservants().size() == 0) {
			setHazard(E.NO_HAZARD);
		} else {
			setHazard(E.MIN_HAZARD);
			for (E e : getConservants()) {
				if (Integer.parseInt(e.getCategory()) > Integer.parseInt(hazard)) {
					setHazard(e.getCategory());
				}
			}
		}
		setConservantFree(E.NO_HAZARD.equals(getHazard()));
	}

	@Basic
	@Column(columnDefinition = "text")
	public String getConservantsText() {
		return conservantsText;
	}

	public void setConservantsText(String conservantsText) {
		this.conservantsText = conservantsText;
	}

	public void setEnteredByIp(String enteredByIp) {
		this.enteredByIp = enteredByIp;
	}

	@Basic
	@Column(length = 1024)
	public String getEnteredByIp() {
		return enteredByIp;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Basic
	@Column(columnDefinition = "text")
	public String getTags() {
		return tags;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	public User getUser() {
		return user;
	}

	@Transient
	public boolean isCreatedBy(User u) {
		return null != this.getUser() && this.getUser().equals(u);
	}
	
	public void setConservantFree(boolean conservantFree) {
		this.conservantFree = conservantFree;
	}

	@Basic
	public boolean isConservantFree() {
		return conservantFree;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	@Basic
	public String getCalories() {
		return calories;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="product", cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	@OrderBy("eventTime DESC")
	public List<Comment> getComments() {
		return comments;
	}

	public void setNotify(boolean notify) {
		this.notify = notify;
	}

	@Basic
	public boolean isNotify() {
		return notify;
	}

	public void addComment(Comment c) {
		getComments().add(0, c);
	}

	public void removeComment(Comment c) {
		if (null != c) {
			Assert.notNull(getComments());

			c.setProduct(null);
			getComments().remove(c);
		}
	}

	public void setApprovedContent(boolean approvedContent) {
		this.approvedContent = approvedContent;
	}

	@Basic
	public boolean isApprovedContent() {
		return approvedContent;
	}

	public void setGmo(boolean gmo) {
		this.gmo = gmo;
	}

	@Basic
	public boolean isGmo() {
		return gmo;
	}

	@Transient
	public String getDescription() {
		StringBuffer ret = new StringBuffer(getName()).append("(").append(getCompany()).append("): ");

		for (E e : conservants) {
			ret.append(e.getDescription()).append(", ");
		}

		return ret.toString();
	}

	@ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = { CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST }, targetEntity = ProductCategory.class)
	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
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
		final Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public void setBaskets(List<ShoppingBasket> baskets) {
		this.baskets = baskets;
	}

	@ManyToMany
	public List<ShoppingBasket> getBaskets() {
		return baskets;
	}

	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
	}

	@Basic
	@Column(columnDefinition="bigint default 0",nullable=false)
	public long getViewCount() {
		return viewCount;
	}

	public void setBarcode(long barcode) {
		this.barcode = barcode;
	}

	@Basic
	public long getBarcode() {
		return barcode;
	}

	public void setLabel(Photo label) {
		this.label = label;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="label")
	public Photo getLabel() {
		return label;
	}

	public void setIngredients(Photo ingredients) {
		this.ingredients = ingredients;
	}

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ingredients")
	public Photo getIngredients() {
		return ingredients;
	}

	public void setLabelPhoto(String labelPhoto) {
		this.labelPhoto = labelPhoto;
	}

	@Transient
	public String getLabelPhoto() {
		return labelPhoto;
	}

	public void setIngredientsPhoto(String ingredientsPhoto) {
		this.ingredientsPhoto = ingredientsPhoto;
	}

	@Transient
	public String getIngredientsPhoto() {
		return ingredientsPhoto;
	}

	public void setChanges(List<ProductChange> changes) {
		this.changes = changes;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="product", cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	public List<ProductChange> getChanges() {
		return changes;
	}
	
	public void addChange(ProductChange change) {
		getChanges().add(change);
	}	

	public void setVoteCount(long voteCount) {
		this.voteCount = voteCount;
	}
	
	@Basic
	@Column(columnDefinition="bigint default 0",nullable=false)
	public long getVoteCount() {
		return voteCount;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	public List<Vote> getVotes() {
		return votes;
	}

	public void setVoteTotal(long total) {
		this.voteTotal = total;
	}
	
	@Basic
	@Column(columnDefinition="bigint default 0",nullable=false)
	public long getVoteTotal() {
		return voteTotal;
	}
	
	public void addVote(Vote vote) {
		if (! getVotes().contains(vote)) {
			getVotes().add(vote);
			
			setVoteTotal(voteTotal + vote.getVote());
		}
	}
	
	@Transient
	public float getRating() {
		return getVotes().size() > 0 ? voteTotal/getVotes().size() : 0; 
	}

	@Basic
	@Column(columnDefinition = "text")
	public String getConservantsParsed() {
		return conservantsParsed;
	}

	public void setConservantsParsed(String conservantsParsed) {
		this.conservantsParsed = conservantsParsed;
	}

	public void setConfirmations(List<Confirmation> confirmations) {
		this.confirmations = confirmations;
		//setConfirmationCount(null == confirmations ? 0 : confirmations.size());
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="product", cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	public List<Confirmation> getConfirmations() {
		return confirmations;
	}
	
	@Transient
	public boolean isConfirmed() {
		return this.getConfirmationsRequired() <= this.getConfirmationCount();
	}

	public void setConfirmationsRequired(Integer confirmationsRequired) {
		this.confirmationsRequired = confirmationsRequired;
	}

	@Transient
	public int getConfirmationsRequired() {
		return confirmationsRequired;
	}
	
	@Transient
	public Integer getReportsRequired() {
		return reportsRequired;
	}
	
	@Transient
	public Confirmation getLatestConfirmation() {
		List<Confirmation> l = this.getConfirmations();
		Confirmation ret = null;
		for (Confirmation c : l) {
			if (ret == null || ret.getEventTime().before(c.getEventTime()))
				ret = c;
		}
		return ret;
	}

	public void setReportsRequired(Integer reportsRequired) {
		this.reportsRequired = reportsRequired;
	}

	public void confirm(Confirmation c) {
		if (! getConfirmations().contains(c)) {
			c.setProduct(this);
			getConfirmations().add(c);
			confirmationCount++;
		}
	}
	
	public void confirmAndOverrideCount(Confirmation c) {
	    confirm(c);
	    confirmationCount = null == confirmationsRequired ? 1 : confirmationsRequired.intValue();
	}

	@Transient
	public boolean isConfirmedBy(User u) {
		logger.debug("IS " + this + " CONFIRMED BY " + u);
		
		for (Confirmation c : this.confirmations) {
			if ((c.getEnteredByIp() != null && c.getEnteredByIp().equalsIgnoreCase(u.getUserAddres())) ||
					c.getActor().getId() == u.getId()) {
				return true;
			}
		}
		
		return false;
	}

	public boolean canBeConfirmedBy(User u) {
	    if (null == u)
	        return false;
		return (!this.isConfirmed() && !this.isConfirmedBy(u));
	}
	
	// Visi gali editint. Balius.
	public boolean canBeEditedBy(User u) {
		return true;
	}
	
	public boolean canBeReportedBy(User u) {
		return (null != u && u.isAdmin()) || !this.isReportedBy(u);
	}	

	public void setConfirmationCount(int confirmationCount) {
		this.confirmationCount = confirmationCount;
	}
	
	@Basic
	@Column(columnDefinition="bigint default 0",nullable=false)
	public int getConfirmationCount() {
		return this.confirmationCount;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="product", cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	public List<Report> getReports() {
		return reports;
	}
	
	public void addReport(Report r) {
		if (! reports.contains(r)) {
			reports.add(r);
		}
	}
	
	@Transient
	public boolean isReportedBy(User u) {
		logger.debug("IS " + this + " REPORTED BY " + u);
		
		for (Report r : this.reports) {
			if ((r.getEnteredByIp() != null && r.getEnteredByIp().equalsIgnoreCase(u.getUserAddres())) ||
					r.getActor().getId() == u.getId()) {
				return true;
			}
		}
		
		return false;		
	}	
	
	@Transient
	public boolean isEditedBy(User u) {
		logger.debug("IS " + this + " EDITED BY " + u);
		return null != u && getChanges().contains(new ProductChange(this, u));
	}

	@Override
	// return true to skip
	public boolean applyFilter(String fieldName) {
		return null != fieldName && 
				! (fieldName.equals("id") || fieldName.equals("name") || fieldName.equals("company") || fieldName.equals("photo") || fieldName.equals("category")
				|| fieldName.equals("tags") || fieldName.equals("conservantFree") || fieldName.equals("calories") || fieldName.equals("gmo")
				|| fieldName.equals("entryDate") || fieldName.equals("conservants") || fieldName.equals("barcode") || fieldName.equals("approved")
				|| fieldName.equals("hazard") || fieldName.equals("label") || fieldName.equals("ingredients"));
	}

	@Override
	public int compareTo(Object arg0) {
		Product other = (Product) arg0;
		
		if (this == other) return 0;
		if (this.isApproved() && !other.isApproved()) return 1;
		if (!this.isApproved() && other.isApproved()) return -1;
		
		return 0;
	}
}
