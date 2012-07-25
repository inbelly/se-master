package com.megalogika.sv.model;

import java.io.Serializable;

public class Transformation implements Serializable {
	private static final long serialVersionUID = -5097947494666092993L;

	public static int PHOTO_DIM_DEFAULT = 217;
	private int x1 = 0;
	private int y1 = 0;
	private int x2 = PHOTO_DIM_DEFAULT;
	private int y2 = PHOTO_DIM_DEFAULT;
	private int height = PHOTO_DIM_DEFAULT;
	private int width = PHOTO_DIM_DEFAULT;
	private double rotation = 0;
	private int maxWidth = 600;
	private int maxHeight = 600;

	public Transformation() {
		// nothing
	}

	public Transformation(int width, int height, int maxW, int maxH) {
		this();
		setWidth(width);
		setX2(width);
		setY2(height);
		setHeight(height);
		setMaxWidth(maxW);
		setMaxHeight(maxH);
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
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

	@Override
	public String toString() {
		return "(" + x1 + "," + y1 + ")" + "(" + x2 + "," + y2 + ")" + "[" + width + "," + height + "] " + rotation + " deg";
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public void rotate(double rotationDeg) {
		if (rotationDeg < 0) {
			rotationDeg = 360 + (rotationDeg % 360);
		}
		double rotMod = rotationDeg % 360;
		rotation += rotMod;
		rotation %= 360;
	}

	public void turnLeft() {
		rotate(-90);
	}

	public void turnRight() {
		rotate(90);
	}

	public void reset() {
		setRotation(0);
		setHeight(PHOTO_DIM_DEFAULT);
		setWidth(PHOTO_DIM_DEFAULT);
		setX1(0);
		setY1(0);
		setY2(PHOTO_DIM_DEFAULT);
		setX2(PHOTO_DIM_DEFAULT);

	}

	public void setMaxHeight(int maxH) {
		this.maxHeight = maxH;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxWidth(int maxW) {
		this.maxWidth = maxW;
	}

	public int getMaxWidth() {
		return maxWidth;
	}

}
