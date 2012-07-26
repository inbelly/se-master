package com.megalogika.sv.service;

import com.megalogika.sv.model.Product;


public class CommentsCriteria extends SearchCriteria {

	private Product product;
	
	public CommentsCriteria(Product p) {
		this();
		setProduct(p);
	}

	public CommentsCriteria() {
		super();
	}

	@Override
	public String getOrderByClause() {
		return "";
	}

	@Override
	public String getWhereClause() {
		return "";
	}

	public void setProduct(Product p) {
		this.product = p;
		setTotalItems(p.getComments().size());
	}

	public Product getProduct() {
		return product;
	}

}
