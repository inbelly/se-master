package com.megalogika.sv.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.util.Assert;

import com.megalogika.sv.model.conversion.JsonFilterable;

@Entity
public class ProductCategory implements Serializable, JsonFilterable {
	private static final long serialVersionUID = 6224017327778099962L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	@Basic
	@Column(length = 1024)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "category")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private List<Product> products;
	
	@Basic
	private int index;

	public ProductCategory() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "[Category: " + name + "]";
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Transient
	public int getProductCount() {
		return getProducts().size();
	}

	@Transient
	public int getApprovedProductsInCategory() {
		Assert.notNull(products);
		
		int ret = 0;
		for (Iterator<Product> product = products.iterator(); product.hasNext();) {
			if (product.next().isApproved()) {
				ret++;
			}
		}
		
		return ret;
	}

	@Override
	public boolean applyFilter(String fieldName) {
		return null != fieldName && 
				!(fieldName.equals("name") || fieldName.equals("id") || fieldName.equals("hazard"));
	}
}
