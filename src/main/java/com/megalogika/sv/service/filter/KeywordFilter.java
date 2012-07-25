package com.megalogika.sv.service.filter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class KeywordFilter extends FirstFilterAware implements Filter, Serializable {

	private static final long serialVersionUID = -5872379944304111668L;
	private String keyword;

	public KeywordFilter(boolean isFirst) {
		super(isFirst);
		setWeight(1111);
	}
	
	public KeywordFilter() {
		this(true);
	}
	
	public KeywordFilter(String query, boolean firstFilter) {
		this(firstFilter);
		setKeyword(query);
	}

	public String getDescription() {
		return "KeywordFilter.desc";
	}

	@Override
	public String getDescriptionArgument() {
		return keyword;
	}
	
	@Override
	public String getFilterClause() {
		return super.getFilterClause() + " (" + "lower(p.category.name) like lower(:keyword) OR " + "lower(p.name) like lower(:keyword) OR "
				+ "lower(p.company) like lower(:keyword) OR" + "lower(p.tags) like lower(:keyword) OR" + "lower(p.conservantstext) like lower(:keyword) " + ")";
	}

	public Map<String, Object> getParameterMap() {
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("keyword", "%" + getKeyword() + "%");
		return ret;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}

}
