package com.megalogika.sv.service.filter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class EFilter extends FirstFilterAware implements Filter, Serializable {

	private static final long serialVersionUID = 742677632800848668L;
	private long eid;
	private String name;

	public EFilter(boolean isFirst) {
		super(isFirst);
		setWeight(7777);
	}
	
	public EFilter() {
		this(true);
	}
	
	public EFilter(long eid, String name, boolean isFirstFilter) {
		this(isFirstFilter);
		this.setEid(eid);
		this.setName(name);
	}

	@Override
	public String getFilterClause() {
		return super.getFilterClause() + " c.id = :eid" + eid;
	}

	public Map<String, Object> getParameterMap() {
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("eid" + eid, eid);
		return ret;
	}

	public void setEid(long eid) {
		this.eid = eid;
	}

	public long getEid() {
		return eid;
	}

	public String getDescription() {
		return isFirstFilter() ? "EFilter.desc.first" : "EFilter.desc";
	}

	@Override
	public String getDescriptionArgument() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (eid ^ (eid >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		EFilter other = (EFilter) obj;
		if (eid != other.eid) {
			return false;
		}
		return true;
	}

}
