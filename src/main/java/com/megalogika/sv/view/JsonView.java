package com.megalogika.sv.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;

import org.springframework.web.servlet.view.AbstractView;

import com.megalogika.sv.controller.ProductListController;
import com.megalogika.sv.model.E;
import com.megalogika.sv.model.Photo;
import com.megalogika.sv.model.Product;
import com.megalogika.sv.model.ProductCategory;
import com.megalogika.sv.model.conversion.JsonFilterable;

public class JsonView extends AbstractView {

	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Product> productList = (List<Product>) model.get(ProductListController.KEY_PRODUCT_LIST);

		if (null == productList) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		} else {
			JSON json = getJSON(productList);
			response.setContentType("application/json; charset=UTF-8");
			json.write(response.getWriter());

		}
	}

	protected JSON getJSON(List<Product> productList) {
		JsonConfig config = new JsonConfig();
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

		config.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				// return true to skip name
				return ! (source instanceof JsonFilterable) || ((JsonFilterable) source).applyFilter(name);
			}
		});
		return JSONSerializer.toJSON(productList, config);
	}

	@Override
	public String getContentType() {
		return "application/json; charset=UTF-8";
	}
}
