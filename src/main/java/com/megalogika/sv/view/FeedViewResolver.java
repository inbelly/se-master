package com.megalogika.sv.view;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.core.Ordered;
import org.springframework.util.Assert;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

public class FeedViewResolver extends AbstractCachingViewResolver implements Ordered {

	private int order;
	
	@Resource
	private JsonView jsonView;
	@Resource
	private FeedView rssView;
	@Resource
	private FeedView atomView;
	
	@Override
	protected View loadView(String viewName, Locale locale) throws Exception {
		Assert.hasText(viewName, "view name must not be null for FeedViewResolver");
		
		if (isJsonView(viewName)) {
			return getJsonView();
		} else
		if (isRssView(viewName)) {
			return getRssView();
		} else
		if (isAtomView(viewName)) {
			return getAtomView();
		} else { // let others handle it
			return null;
		}
	}

	private boolean isJsonView(String viewName) {
		Assert.hasText(viewName, "view name must not be null for FeedViewResolver");
		
		return viewName.toLowerCase().endsWith("json"); 
	}
	
	private boolean isRssView(String viewName) {
		Assert.hasText(viewName, "view name must not be null for FeedViewResolver");
		
		return viewName.toLowerCase().endsWith("rss"); 
	}
	
	private boolean isAtomView(String viewName) {
		Assert.hasText(viewName, "view name must not be null for FeedViewResolver");
		
		return viewName.toLowerCase().endsWith("atom"); 
	}	

	@Override
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void setJsonView(JsonView jsonView) {
		this.jsonView = jsonView;
	}

	public JsonView getJsonView() {
		return jsonView;
	}

	public void setRssView(FeedView rssView) {
		this.rssView = rssView;
	}

	public FeedView getRssView() {
		return rssView;
	}

	public void setAtomView(FeedView atomView) {
		this.atomView = atomView;
	}

	public FeedView getAtomView() {
		return atomView;
	}

}
