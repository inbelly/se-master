package com.megalogika.sv.model.conversion;

import java.beans.PropertyEditorSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.megalogika.sv.model.ProductCategory;
import com.megalogika.sv.service.CategoryService;

@Component
public class ProductCategoryPropertyEditor extends PropertyEditorSupport {
	protected transient Logger logger = Logger.getLogger(ProductCategoryPropertyEditor.class);
	
	@Autowired
	private CategoryService categoryService;
	
	public ProductCategoryPropertyEditor() {
		super();
	}
	
	public ProductCategoryPropertyEditor(CategoryService categoryService) {
		this();
		setCategoryService(categoryService);
	}
	
	@Override
	public void setAsText(String text) {
		if (! StringUtils.hasText(text)) {
			setValue(null);
		} else {
			try {
				setValue(getCategoryService().getProductCategory(Long.parseLong(text)));
			} catch (Exception e) {
				setValue(null);
			}
		}
	}	
	
	@Override
	public String getAsText() {
		try {
			return Long.valueOf(((ProductCategory)getValue()).getId()).toString();
		} catch (Exception e) {
			return "";
		}
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}
}
