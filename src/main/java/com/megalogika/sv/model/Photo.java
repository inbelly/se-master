package com.megalogika.sv.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.megalogika.sv.model.conversion.JsonFilterable;
import com.megalogika.sv.service.CropService;
import com.megalogika.sv.service.CropServiceException;
import com.megalogika.sv.service.PhotoDimension;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Photo implements Serializable, JsonFilterable {
	private static final long serialVersionUID = -2319247745089352558L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;

	String photo;
	String originalPhoto;
	String resizedPhoto;

	private int height;
	private int width;

	@Transient
	private Transformation cropping = new Transformation();

	@Override
	public String toString() {
		return "Photo[" + photo + "]" + cropping;
	}
	
	@Basic
	@Column(length = 1024)
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Basic
	@Column(length = 1024)
	public String getOriginalPhoto() {
		return originalPhoto;
	}

	public void setOriginalPhoto(String originalPhoto) {
		this.originalPhoto = originalPhoto;
	}

	@Basic
	@Column(length = 1024)
	public String getResizedPhoto() {
		return resizedPhoto;
	}

	public void setResizedPhoto(String resizedPhoto) {
		this.resizedPhoto = resizedPhoto;
	}

	@Transient
	public Transformation getCropping() {
		return cropping;
	}

	public void setCropping(Transformation cropping) {
		this.cropping = cropping;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	public void assignDimensions(PhotoDimension dims) {
		setWidth(dims.getWidth());
		setHeight(dims.getHeight());
	}

	public void updateMeasurements(CropService cropService) throws IOException, CropServiceException {
		updateMeasurements(cropService.getImage(getOriginalPhoto()));
	}

	public void updateMeasurements(BufferedImage inImage) {
		setWidth(inImage.getWidth());
		setHeight(inImage.getHeight());
		
		getCropping().setWidth(getWidth());
		getCropping().setHeight(getHeight());
		
		getCropping().setMaxWidth(getWidth());
		getCropping().setMaxHeight(getHeight());
	
		getCropping().setX1(0);
		getCropping().setY1(0);
		getCropping().setX2(getWidth());
		getCropping().setY2(getHeight());
	}

	@Override
	public boolean applyFilter(String fieldName) {
		return null != fieldName && 
				!(fieldName.equals("photo") || fieldName.equals("originalPhoto") || fieldName.equals("resizedPhoto"));
	}

}
