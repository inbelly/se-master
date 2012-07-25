package com.megalogika.sv.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.megalogika.sv.service.ProductService;
import com.megalogika.sv.util.SuggestEntry;

@Controller
public class CompaniesController {
	@Autowired
	private ProductService productService;

	@RequestMapping("/companies")
	public ModelMap ownerHandler(@RequestParam(required=true,value="q") String q) {
		Assert.notNull(q);
		
		ModelMap m = new ModelMap();
		
		List<SuggestEntry> suggestList = new ArrayList<SuggestEntry>();

		String query = new String(q).toLowerCase();

		if (query.startsWith("uab")) {
			query = query.substring(3).trim();
		}
		
		if (query.length() < 4) {
			m.addAttribute("jsonList", "[]");
			return m;
		}

		for (Iterator<String> i = productService.findCompanies(query).iterator(); i.hasNext();) {
			suggestList.add(new SuggestEntry(i.next()));
		}

		m.addAttribute("jsonList", JSONSerializer.toJSON(suggestList).toString());
		
		return m;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public ProductService getProductService() {
		return productService;
	}
	
}
