package com.megalogika.sv.service.filter;

import java.util.Map;

public class ApprovedContentFilter extends FirstFilterAware implements Filter {

	public ApprovedContentFilter(boolean firstFilter) {
		super(firstFilter);
		setWeight(200);
	}

	public ApprovedContentFilter() {
		this(true);
	}
	

	public String getDescription() {
		return isFirstFilter() ? "ApprovedContentFilter.desc.first" : "ApprovedContentFilter.desc";
	}

	@Override
	public String getFilterClause() {
		return super.getFilterClause() + " p.approvedContent = true ";
	}

	public Map<String, Object> getParameterMap() {
		return null;
	}

	@Override
	public String getDescriptionArgument() {
		return null;
	}

}
