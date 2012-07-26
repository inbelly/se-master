package com.megalogika.sv.util;

import com.megalogika.sv.model.E;

public class SuggestEntry {
	private String id;
	private String value;
	private String info;

	public SuggestEntry(E e) {
		this.id = e.getNumber();
		this.value = e.getNumber();
		this.info = e.getName();
	}

	public SuggestEntry(String s) {
		this.id = s;
		this.value = s;
		this.info = "";
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}
}