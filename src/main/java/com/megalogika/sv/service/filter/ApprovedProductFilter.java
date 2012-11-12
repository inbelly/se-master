package com.megalogika.sv.service.filter;

import java.util.Map;

public class ApprovedProductFilter extends FirstFilterAware implements Filter {

	public ApprovedProductFilter(boolean firstFilter) {
		super(firstFilter);
		setWeight(10999);
	}

	public ApprovedProductFilter() {
		this(true);
	}
	

	@Override
	public String getDescription() {
		return isFirstFilter() ? "ApprovedProductFilter.desc.first" : "ApprovedProductFilter.desc";
	}

	@Override
	public String getFilterClause() {
		return super.getFilterClause() + " p.approved = true ";
	}

	public Map<String, Object> getParameterMap() {
		return null;
	}

	@Override
	public String getDescriptionArgument() {
		return "ApprovedProductFilter";
	}

}