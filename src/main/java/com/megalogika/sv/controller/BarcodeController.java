package com.megalogika.sv.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.megalogika.sv.model.Product;
import com.megalogika.sv.service.ProductService;
import com.megalogika.sv.service.UserService;

@Controller("barcodeController")
public class BarcodeController {

	protected transient Logger logger = Logger.getLogger(BarcodeController.class);
	
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private FrontendService frontendService;
	
	@RequestMapping("/checkBarcode")
	public String checkBarcode(HttpServletResponse response, HttpSession session, 
			@RequestParam(required=true, value="barcode") String barcode) throws IOException 
	{
		if (! StringUtils.hasText(barcode)) {
			logger.warn("No product barcode specified!");
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
			return null;
		} else {
			Product product = getProductService().getOneByBarcode(barcode);
			if (null == product) {
				session.setAttribute("barcode", barcode);
				if (null == userService.getCurrentUser()) {
					return "redirect:/spring/barcodeNotFound";
				} else {
					return "redirect:/spring/createProduct";
				}
			} else {
				return "redirect:/spring/foundBarcode?id=" + product.getId();
			}
		}
	}

	@RequestMapping("/barcodeNotFound")
	public ModelAndView barcodeNotFound(HttpServletRequest request) {
		return new ModelAndView("barcodeNotFound", frontendService.makeModelMap(request));
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public ProductService getProductService() {
		return productService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public UserService getUserService() {
		return userService;
	}

	public void setFrontendService(FrontendService frontendService) {
		this.frontendService = frontendService;
	}

	public FrontendService getFrontendService() {
		return frontendService;
	}
 	
}
