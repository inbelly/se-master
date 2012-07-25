package com.megalogika.sv.service.filter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CompanyFilter extends FirstFilterAware implements Filter, Serializable {
	private static final long serialVersionUID = 5265935287294900873L;
	private String company;

	public CompanyFilter(boolean isFirst) {
		super(isFirst);
		setWeight(8888);
	}
	
	public CompanyFilter() {
		this(true);
	}
	
	public CompanyFilter(String company, boolean isFirstFilter) {
		this(isFirstFilter);
		this.company = company;
	}

	public String getDescription() {
		return isFirstFilter() ? "CompanyFilter.desc.first" : "CompanyFilter.desc";
	}

	@Override
	public String getDescriptionArgument() {
		return company;
	}

	@Override
	public String getFilterClause() {
		return super.getFilterClause() + " p.company = :company";
	}

	public Map<String, Object> getParameterMap() {
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("company", company);
		return ret;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
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
		CompanyFilter other = (CompanyFilter) obj;
		if (company == null) {
			if (other.company != null) {
				return false;
			}
		} else if (!company.equals(other.company)) {
			return false;
		}
		return true;
	}

}
