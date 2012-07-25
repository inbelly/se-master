package com.megalogika.sv.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.Authentication;
import org.springframework.security.ui.TargetUrlResolver;
import org.springframework.security.ui.savedrequest.SavedRequest;

public class InBellyTargetUrlResolver implements TargetUrlResolver {
	protected transient Logger logger = Logger.getLogger(InBellyTargetUrlResolver.class);
	
	private final TargetUrlResolver defaultSpringSecurityUrlResolver;

	public InBellyTargetUrlResolver(final TargetUrlResolver fallbackTargetUrlResolver) {
		defaultSpringSecurityUrlResolver = fallbackTargetUrlResolver;
	}

	@Override
	/**
	 * @param savedRequest The request that initiated the authentication process
	 * @param currentRequest the current request
	 * @param auth The authentication token generated after successful authentication
	 * @return The URL to be used 
	 */
	public String determineTargetUrl(SavedRequest savedRequest, HttpServletRequest currentRequest, Authentication auth) {
		logger.debug("SAVED: " + (null != savedRequest ? savedRequest.getRequestUrl() : " NICHUJA NE SAVED (null)"));

		return defaultSpringSecurityUrlResolver.determineTargetUrl(savedRequest, currentRequest, auth);
	}

	public TargetUrlResolver getDefaultSpringSecurityUrlResolver() {
		return defaultSpringSecurityUrlResolver;
	}

}
