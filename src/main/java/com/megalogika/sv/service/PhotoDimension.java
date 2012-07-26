package com.megalogika.sv.service;

import java.io.Serializable;

public class PhotoDimension implements Serializable {
	
	private static final long serialVersionUID = -4695533732972162545L;

	private int width;
	private int height;
	
	public PhotoDimension(int width, int height) {
		this.height = height;
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

}
