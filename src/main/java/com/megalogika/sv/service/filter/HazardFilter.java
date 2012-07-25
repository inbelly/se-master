package com.megalogika.sv.service.filter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.megalogika.sv.model.E;
import com.megalogika.sv.service.EService;

public class HazardFilter extends FirstFilterAware implements Filter, Serializable {
	private static final long serialVersionUID = -8175404679382529374L;

	private String hazard;
	private transient EService eService;

	public HazardFilter(boolean isFirst) {
		super(isFirst);
		setWeight(5555);
	}
	
	public HazardFilter() {
		this(true);
	}
	
	public HazardFilter(String hazard, EService eService, boolean firstFilter) {
		super(firstFilter);
		this.hazard = hazard;
		this.eService = eService;
	}

	public String getDescription() {
		if (E.NO_HAZARD.equals(hazard)) {
			return isFirstFilter() ? "HazardFilter.noe.desc.first" : "HazardFilter.noe.desc";
		}
		return isFirstFilter() ? "HazardFilter.desc.first" : "HazardFilter.desc";
	}

	@Override
	public String getDescriptionArgument() {
		return eService.getHazardDescription(hazard);
	}

	@Override
	public String getFilterClause() {
		return super.getFilterClause() + " p.hazard = :hazard";
	}

	public Map<String, Object> getParameterMap() {
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("hazard", hazard);
		return ret;
	}

	public void setHazard(String hazard) {
		this.hazard = hazard;
	}

	public String getHazard() {
		return hazard;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hazard == null) ? 0 : hazard.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HazardFilter other = (HazardFilter) obj;
		if (hazard == null) {
			if (other.hazard != null)
				return false;
		} else if (!hazard.equals(other.hazard))
			return false;
		return true;
	}

	public void setEService(EService eService) {
		this.eService = eService;
	}

	public EService getEService() {
		return eService;
	}

}
