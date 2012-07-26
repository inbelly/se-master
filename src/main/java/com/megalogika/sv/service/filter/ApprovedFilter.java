package com.megalogika.sv.service.filter;

import java.util.Map;

public class ApprovedFilter extends FirstFilterAware implements Filter {

	public ApprovedFilter(boolean firstFilter) {
		super(firstFilter);
		setWeight(10999);
	}

	public ApprovedFilter() {
		this(true);
	}
	

	public String getDescription() {
		return "";
	}

	@Override
	public String getFilterClause() {
		return super.getFilterClause() + " p.confirmationCount > 1 ";
	}

	public Map<String, Object> getParameterMap() {
		return null;
	}

	@Override
	public String getDescriptionArgument() {
		return null;
	}
	
}
