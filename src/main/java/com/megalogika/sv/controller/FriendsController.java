package com.megalogika.sv.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.megalogika.sv.model.Donator;
import com.megalogika.sv.model.Partner;
import com.megalogika.sv.service.FriendService;
import com.megalogika.sv.service.UploadService;

@Controller("friendsController")
public class FriendsController {

	protected transient Logger logger = Logger.getLogger(FriendsController.class);
	
	public static final String KEY_PARTNERS = "partners";
	public static final String KEY_DONATORS = "donators";

	@Autowired
	private FrontendService frontendService;
	@Autowired
	private FriendService friendService;
	@Autowired
	private UploadService uploadService;
	
	@RequestMapping("/friends")
	public ModelAndView getDonatorsAndPartners(HttpServletRequest request) {
		ModelMap ret = new ModelMap();
		
		ret.addAttribute(KEY_DONATORS, getFriendService().getListOfDonators());
		ret.addAttribute(KEY_PARTNERS, getFriendService().getListOfPartners());
		ret.addAttribute(new Partner());
		ret.addAttribute(new Donator());
		
		frontendService.addCurrentUser(ret);
		frontendService.addHazardDescriptions(ret);
		frontendService.addContextPath(ret, request);
		
		return new ModelAndView("friends", ret);
	}
	
	@InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
	    throws ServletException {
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    simpleDateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false, 10));
	}	
	
	@RequestMapping(value="/partner/add", method={RequestMethod.POST})
	public String addPartner(HttpServletRequest request, HttpServletResponse response, @ModelAttribute(value="partner") Partner partner) {
		
		uploadService.upload(partner);
		friendService.add(partner);
		
		return "redirect:/spring/friends";
	}
	
	@RequestMapping(value="/donator/add", method={RequestMethod.POST})
	public String addPartner(HttpServletRequest request, HttpServletResponse response, @ModelAttribute(value="donator") Donator donator) {
		
		uploadService.upload(donator);
		friendService.add(donator);
		
		return "redirect:/spring/friends";
	}
	
	@RequestMapping("/partner/delete")
	public String deletePartner(HttpServletResponse response, @RequestParam(required=true, value="id") long id) throws IOException {
		
		Partner p = friendService.loadPartner(id);
		if (null == p) {
			logger.error("No partner for id: " + id);
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return "redirect:/spring/404";
		}
		friendService.delete(p);
		
		return "redirect:/spring/friends";
	}
	
	@RequestMapping("/donator/delete")
	public String deleteDonator(HttpServletResponse response, @RequestParam(required=true, value="id") long id) throws IOException {
		
		Donator p = friendService.loadDonator(id);
		if (null == p) {
			logger.error("No Donator for id: " + id);
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return "redirect:/spring/404";
		}
		friendService.delete(p);
		
		return "redirect:/spring/friends";
	}	
	
	public void setFrontendService(FrontendService frontendService) {
		this.frontendService = frontendService;
	}

	public FrontendService getFrontendService() {
		return frontendService;
	}

	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}

	public FriendService getFriendService() {
		return friendService;
	}

	public void setUploadService(UploadService uploadService) {
		this.uploadService = uploadService;
	}

	public UploadService getUploadService() {
		return uploadService;
	}
}