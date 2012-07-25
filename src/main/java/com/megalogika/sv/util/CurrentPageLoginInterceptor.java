package com.megalogika.sv.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.ui.AbstractProcessingFilter;
import org.springframework.security.ui.savedrequest.SavedRequest;
import org.springframework.security.util.PortResolver;
import org.springframework.security.util.PortResolverImpl;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CurrentPageLoginInterceptor extends HandlerInterceptorAdapter {

	protected Logger logger = Logger.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		setSavedRequest(request);
		return super.preHandle(request, response, handler);
	}

	private SavedRequest defineReferer(HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		if (!StringUtils.hasText(referer)) {
			referer = request.getContextPath();
		}
		logger.debug("Came here from: " + referer);
		logger.debug("WHAT WE HAVE AS REQUEST: " + request.getRequestURI());
		SavedRequest savedRequest = getSavedRequest(request);
		if (null != savedRequest) {
			logger.debug("WHAT WE HAVE SAVED: " + savedRequest.getRequestUrl());
			if (null != savedRequest.getRequestUrl() && 
					(savedRequest.getRequestUrl().contains("createProduct") && ! savedRequest.getRequestUrl().contains("execution")) ||
					savedRequest.getRequestUrl().contains("foundBarcode")) 
			{
				return savedRequest;
			}
		}

		return new SavedReferrerRequest(request, new PortResolverImpl(), referer);
	}

	private SavedRequest getSavedRequest(HttpServletRequest request) {
		return (SavedRequest) request.getSession(true).getAttribute(
				AbstractProcessingFilter.SPRING_SECURITY_SAVED_REQUEST_KEY);
	}
	
	private void setSavedRequest(HttpServletRequest request) {
		request.getSession(true).setAttribute(
				AbstractProcessingFilter.SPRING_SECURITY_SAVED_REQUEST_KEY, defineReferer(request));
	}
}

class SavedReferrerRequest extends SavedRequest {
	private static final long serialVersionUID = 1L;
	
	String refererUrl;
	
	public SavedReferrerRequest(HttpServletRequest req, PortResolver prtRslv, String ref) {
		super(req, prtRslv);
		refererUrl = ref;
	}
	
	@Override
	public String getFullRequestUrl() {
		return refererUrl;
	}
	
	@Override
	public String getRequestUrl() {
		return refererUrl;
	}
	
	@Override
	public String getRequestURL() {
		return refererUrl;
	}
}
