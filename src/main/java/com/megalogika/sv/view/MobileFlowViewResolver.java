package com.megalogika.sv.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.View;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.mvc.builder.DelegatingFlowViewResolver;
import org.springframework.webflow.mvc.view.FlowViewResolver;

public class MobileFlowViewResolver extends DelegatingFlowViewResolver implements FlowViewResolver {

	public MobileFlowViewResolver(List viewResolvers) {
		super(viewResolvers);
	}

	@Override
	public String getViewIdByConvention(String viewStateId) {
		return super.getViewIdByConvention(viewStateId);
	}

	@Override
	public View resolveView(String viewId, RequestContext context) {
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getNativeRequest();
		String viewName = viewId;

		if (req.getServerName().startsWith("m.") || null != req.getParameter("mobile")) {
			viewName = viewId + ".mobile";
		}

		return super.resolveView(viewName, context);
	}

}
