package com.megalogika.sv.service.filter;

import java.util.Map;

public interface Filter extends Comparable<Filter> {
	
	public String getFilterClause();

	public String getDescription();

	public String getDescriptionArgument();

	public Map<String, Object> getParameterMap();
	
	public int getWeight();

}
