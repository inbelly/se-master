package com.megalogika.sv.service.filter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.megalogika.sv.model.ProductCategory;

public class CategoryFilter extends FirstFilterAware implements Filter, Serializable {
	private static final long serialVersionUID = 2201659284134902786L;
	private ProductCategory category;

	public CategoryFilter(boolean isFirst) {
		super(isFirst);
		setWeight(9999);
	}
	
	public CategoryFilter() {
		this(true);
	}
	
	public CategoryFilter(ProductCategory category, boolean isFirstFilter) {
		this(isFirstFilter);
		this.category = category;
	}

	public String getDescription() {
		return "CategoryFilter.desc.first";
	}

	@Override
	public String getDescriptionArgument() {
		return category.getName();
	}

	@Override
	public String getFilterClause() {
		return super.getFilterClause() + " p.category.id = :category";
	}

	public Map<String, Object> getParameterMap() {
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("category", category.getId());
		return ret;
	}

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
		result = prime * result + ((category == null) ? 0 : category.hashCode());
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
		CategoryFilter other = (CategoryFilter) obj;
		if (category == null) {
			if (other.category != null) {
				return false;
			}
		} else if (!category.equals(other.category)) {
			return false;
		}
		return true;
	}

}
