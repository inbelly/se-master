package com.megalogika.sv.service.filter;

import java.util.Map;

public class GmoFilter extends FirstFilterAware implements Filter {

	public GmoFilter() {
		super(true);
		setWeight(6666);
	}

	@Override
	public String getDescription() {
		return "GmoFilter.desc";
	}

	@Override
	public String getFilterClause() {
		return super.getFilterClause() + " p.gmo = true";
	}

	@Override
	public Map<String, Object> getParameterMap() {
		return null;
	}

	@Override
	public String getDescriptionArgument() {
		return null;
	}

}
