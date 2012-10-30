package com.megalogika.sv.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("dataSourcesController")
public class DataSourcesController {
	
	@Autowired
	private
	FrontendService frontendService;	
	
	@RequestMapping("/dataSources")
	public ModelAndView createDataSourcePage(HttpServletRequest request) {
		return new ModelAndView("dataSources", frontendService.makeModelMap(request));
	}
}
