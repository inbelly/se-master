package com.megalogika.sv.model.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import com.megalogika.sv.model.Product;

@Component
public class ProductValidator {
	protected transient Logger logger = Logger.getLogger(ProductValidator.class);
	
	public void validateEditIngredients(Product p, Errors errors) {
		logger.debug("Product VALIDATOR IS VALIDATING");
		logger.debug("FREE: " + p.isConservantFree());
		logger.debug("CONS: " + p.getConservantsText());
		if (p.isConservantFree()) {
			if (StringUtils.hasText(p.getConservantsText())) {
				errors.rejectValue("conservantsText", "product.error.conservantfree", "Nurodykite priedų sąrašą arba pažymėkite \"Nėra\" varnelę");
			}
		} else {
			if (! StringUtils.hasText(p.getConservantsText())) {
				errors.rejectValue("conservantsText", "product.error.noconservants", "Nurodykite priedų sąrašą arba pažymėkite \"Nėra\" varnelę");
			}
		}
	}
}
