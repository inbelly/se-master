package com.megalogika.sv.model.conversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.convert.converters.StringToObject;
import org.springframework.stereotype.Component;

import com.megalogika.sv.model.ProductCategory;
import com.megalogika.sv.service.CategoryService;

@Component
public class StringToProductCategory extends StringToObject {

	@Autowired
	private CategoryService categoryService;

	public StringToProductCategory() {
		super(ProductCategory.class);
	}
	
	public StringToProductCategory(CategoryService categoryService) {
		super(ProductCategory.class);
		this.categoryService = categoryService;
	}

	@Override
	protected Object toObject(String id, Class arg1) throws Exception {
		return categoryService.getProductCategory(Long.parseLong(id));
	}

	@Override
	protected String toString(Object o) throws Exception {
		assert (o instanceof ProductCategory);
		ProductCategory c = (ProductCategory) o;
		return Long.valueOf(c.getId()).toString();
	}

}
