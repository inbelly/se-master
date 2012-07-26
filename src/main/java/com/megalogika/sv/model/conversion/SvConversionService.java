package com.megalogika.sv.model.conversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.convert.service.DefaultConversionService;
import org.springframework.stereotype.Service;

import com.megalogika.sv.service.CategoryService;

@Service("sveikasVaikasConversionService")
public class SvConversionService extends DefaultConversionService {
	protected transient static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SvConversionService.class);

	CategoryService categoryService;

	@Autowired
	public SvConversionService(CategoryService categoryService) {
		super();
		setCategoryService(categoryService);
		addCustomConverters();
	}

	protected void addCustomConverters() {
		if (null != categoryService) {
			addConverter("stringToProductCategory", new StringToProductCategory(categoryService));
		} else {
			logger.error("\n\n\n COULD NOT ADD STRING TO PRODUCT CATEGORY converter - no categoryService!!!\n\n\n");
		}
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
}
