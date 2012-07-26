package com.megalogika.sv.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.megalogika.sv.model.Product;
import com.megalogika.sv.model.User;
import com.megalogika.sv.service.BasketService;
import com.megalogika.sv.service.ProductService;
import com.megalogika.sv.service.UserService;

@Controller("shoppingBasketController")
public class ShoppingBasketController {

	protected transient Logger logger = Logger.getLogger(ProductController.class);
	
	@Autowired
	private BasketService basketService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private FrontendService frontendService;
	
	private ModelMap prepareShoppingBasket(HttpSession session, HttpServletResponse response) throws IOException, InstantiationException, IllegalAccessException {
		User user = getUserService().getCurrentUser();
		user = userService.merge(user);
		
//		if (null == user) {
			logger.error("User not found, can not show basket!");
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
//		} else {
//			ModelMap m = new ModelMap();
//			
//			List<Product> products = null;
//			if (null != user.getShoppingBasket()) {
//				products = user.getShoppingBasket().getProducts();
//			} else {
//				products = new ArrayList<Product>();
//			}
//			
//			ProductSearchCriteria criteria = (ProductSearchCriteria) frontendService.getCriteria(session, ProductSearchCriteria.class);
//			criteria.clear();
//
//			frontendService.addCurrentUser(m);
//			frontendService.addHazardDescriptions(m);
//			m.addAttribute(FrontendService.KEY_PRODUCTS, products);
//			
//			return m;
//		}
	}
	
	@RequestMapping("/basket")
	@Secured({"ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN", "IS_AUTHENTICATED_ANONYMOUSLY"})
	public ModelAndView getShoppingBasket(HttpServletResponse response, HttpSession session) throws IOException, InstantiationException, IllegalAccessException {
		return new ModelAndView("shoppingBasket", prepareShoppingBasket(session, response));
	}
	
	@RequestMapping("/printbasket")
	@Secured({"ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN", "IS_AUTHENTICATED_ANONYMOUSLY"})
	public ModelAndView getProduct(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, InstantiationException, IllegalAccessException {
		return new ModelAndView("shoppingBasketPrint", prepareShoppingBasket(session, response));
	}
	
	@RequestMapping("/basket/add")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public String addToBasket(HttpServletRequest request, HttpServletResponse response, @RequestParam(required=true, value="id") String id) throws IOException {
		
		User user = getUserService().getCurrentUser();
		if (null == user) {
			logger.error("User not found, can not put product into basket: " + id);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return "redirect:/spring/500";
		} else {
			Product product = getProductService().loadProduct(id);
			
			if (null == product) {
				logger.error("Product not found, cannot put into basket: " + id);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return "redirect:/spring/404";
			} else {
				basketService.putProductToBasket(user, product);
				request.getSession().setAttribute(ProductController.KEY_ADDED_TO_BASKET, "true");
				
				return "redirect:/spring/basket";
			}
		}
	}
	
	@RequestMapping("/basket/remove")
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public String removeFromBasket(HttpServletRequest request, HttpServletResponse response, @RequestParam(required=true, value="id") String id) throws IOException {
		
		User user = getUserService().getCurrentUser();
		if (null == user) {
			logger.error("User not found, can not remove product from basket: " + id);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return "redirect:/spring/500";
		} else {
			Product product = getProductService().loadProduct(id);
			
			if (null == product) {
				logger.error("Product not found, cannot remove from basket: " + id);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return "redirect:/spring/404";
			} else {
				basketService.removeProductFromBasket(user, product);
				request.getSession().setAttribute(ProductController.KEY_REMOVED_FROM_BASKET, "true");
				
				return "redirect:/spring/basket";
			}
		}
	}	
	
	public void setBasketService(BasketService basketService) {
		this.basketService = basketService;
	}

	public BasketService getBasketService() {
		return basketService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setFrontendService(FrontendService frontendService) {
		this.frontendService = frontendService;
	}

	public FrontendService getFrontendService() {
		return frontendService;
	}
	
}
