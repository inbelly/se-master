package com.megalogika.sv.service.filter;

public abstract class FirstFilterAware implements Filter {
	
	private boolean isFirstFilter;
	private int weight = 0;

	public FirstFilterAware() {
		setFirstFilter(false);
	}
	
	public FirstFilterAware(boolean first) {
		setFirstFilter(first);
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getWeight() {
		return this.weight;
	}

	public void setFirstFilter(boolean isFirstFilter) {
		this.isFirstFilter = isFirstFilter;
	}

	public boolean isFirstFilter() {
		return isFirstFilter;
	}
	
	public String getFilterClause() {
		return isFirstFilter() ? "" : " and";
	}
	
	public int compareTo(Filter o) {
		return null == o ? 1 : getWeight() - o.getWeight();
	}
	
}
