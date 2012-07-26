package com.megalogika.sv.controller;

import org.apache.log4j.Logger;

import com.megalogika.sv.model.ProductCategory;

public class ProductEditCommand {
	protected transient Logger logger = Logger.getLogger(ProductEditCommand.class);
	
	String field = null;
	String value = null;
	
	String id = null;
	
	boolean unique = false;
	
	ProductCategory category = null;

	@Override
	public String toString() {
		return "EDIT["+id+"field: '"+field+"' value: '"+value+"' unique: "+unique+" category: "+category;
	}
	
	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getField() {
		return field;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setValue(String value) {
		logger.debug("NEW VALUE: " + value);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public boolean isUnique() {
		return unique;
	}
}
