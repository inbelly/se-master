package com.megalogika.sv.view;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator;

public class RequestToViewNameTranslator extends
		DefaultRequestToViewNameTranslator {
	protected transient Logger logger = Logger.getLogger(getClass());

	/**
	 * Translates the request URI of the incoming {@link HttpServletRequest}
	 * into the view name based on the configured parameters.
	 * 
	 * @see org.springframework.web.util.UrlPathHelper#getLookupPathForRequest
	 * @see #transformPath
	 */
	@Override
	public String getViewName(HttpServletRequest request) {
		return super.getViewName(request);
	}
}
