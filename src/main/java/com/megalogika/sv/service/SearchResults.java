package com.megalogika.sv.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.megalogika.sv.model.E;
import com.megalogika.sv.model.Product;

public class SearchResults implements Serializable {
	private static final long serialVersionUID = 9124504694387454515L;

	boolean conservant = false;
	boolean product = false;
	private boolean searchedByBarcode = false;
	List<E> conservants = new ArrayList<E>();
	List<Product> products = new ArrayList<Product>();

	private String query;
	
	public boolean isConservant() {
		return conservant;
	}

	public void setConservant(boolean conservant) {
		this.conservant = conservant;
	}

	public boolean isProduct() {
		return product;
	}

	public void setProduct(boolean product) {
		this.product = product;
	}

	public List<E> getConservants() {
		return conservants;
	}

	public void setConservants(List<E> conservants) {
		this.conservants = conservants;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

	public void setSearchedByBarcode(boolean searchedByBarcode) {
		this.searchedByBarcode = searchedByBarcode;
	}

	public boolean isSearchedByBarcode() {
		return searchedByBarcode;
	}
}
