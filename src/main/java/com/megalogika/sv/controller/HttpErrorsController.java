package com.megalogika.sv.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HttpErrorsController {

	@Autowired
	private FrontendService frontendService;

	@RequestMapping(value = "/400")
	public ModelAndView handle400(HttpServletRequest request) {
		return new ModelAndView("400", getFrontendService().makeModelMap(request));
	}

	@RequestMapping(value = "/403")
	public ModelAndView handle403(HttpServletRequest request) {
		return new ModelAndView("403", getFrontendService().makeModelMap(request));
	}

	@RequestMapping(value = "/404")
	public ModelAndView handle404(HttpServletRequest request) {
		return new ModelAndView("404", getFrontendService().makeModelMap(request));
	}

	@RequestMapping(value = "/500")
	public ModelAndView handle500(HttpServletRequest request) {
		return new ModelAndView("500", getFrontendService().makeModelMap(request));
	}

	@RequestMapping(value = "/401")
	public ModelAndView handle401(HttpServletRequest request) {
		return new ModelAndView("401", getFrontendService().makeModelMap(request));
	}

	@RequestMapping(value = "/502")
	public ModelAndView handle502(HttpServletRequest request) {
		return new ModelAndView("502", getFrontendService().makeModelMap(request));
	}

	@RequestMapping(value = "/503")
	public ModelAndView handle503(HttpServletRequest request) {
		return new ModelAndView("503", getFrontendService().makeModelMap(request));
	}
	
	@RequestMapping(value = "/409")
	public ModelAndView handle409(HttpServletRequest request) {
		return new ModelAndView("409", getFrontendService().makeModelMap(request));
	}	
	
	@RequestMapping(value = "/barcodeNoFound")
	public ModelAndView handleBarcodeNotFound(HttpServletRequest request) {
		return new ModelAndView("barcodeNotFound", getFrontendService().makeModelMap(request));
	}		

	public void setFrontendService(FrontendService frontendService) {
		this.frontendService = frontendService;
	}

	public FrontendService getFrontendService() {
		return frontendService;
	}
}
