package com.megalogika.sv.service.filter;

import java.util.Map;

public class UnapprovedProductFilter extends FirstFilterAware implements Filter {

	public UnapprovedProductFilter(boolean firstFilter) {
		super(firstFilter);
		setWeight(200);
	}

	public UnapprovedProductFilter() {
		this(true);
	}
	

	public String getDescription() {
		return isFirstFilter() ? "UnapprovedProductFilter.desc.first" : "UnapprovedProductFilter.desc";
	}

	@Override
	public String getFilterClause() {
		return super.getFilterClause() + " p.approved = false ";
	}

	public Map<String, Object> getParameterMap() {
		return null;
	}

	@Override
	public String getDescriptionArgument() {
		return "UnapprovedProductFilter";
	}

}
