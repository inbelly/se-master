package com.megalogika.sv.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("securityPagesController")
public class SecurityController {

	@Autowired
	private
	FrontendService frontendService;
	
	@RequestMapping(value="/login")
    public ModelAndView handleLogin(HttpServletRequest request) {
    	return new ModelAndView("login", frontendService.makeModelMap(request));
    }
    
    @RequestMapping(value="/loginProcess")
    public String handleLoginProcess() {
    	return "loginProcess";
    }
    
    @RequestMapping(value="/loginError")
    public ModelAndView handleLoginError(HttpServletRequest request) {
    	return new ModelAndView("loginError", frontendService.makeModelMap(request));
    }
    
    @RequestMapping(value="/logout")
    public String handleLogout() {
    	return "logout";
    }

	public void setFrontendService(FrontendService frontendService) {
		this.frontendService = frontendService;
	}

	public FrontendService getFrontendService() {
		return frontendService;
	}
    
}
