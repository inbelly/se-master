package com.megalogika.sv.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.AbstractView;

import com.megalogika.sv.controller.ProductListController;
import com.megalogika.sv.model.Product;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.feed.synd.SyndImage;
import com.sun.syndication.feed.synd.SyndImageImpl;
import com.sun.syndication.feed.synd.SyndPerson;
import com.sun.syndication.feed.synd.SyndPersonImpl;
import com.sun.syndication.io.SyndFeedOutput;

public class FeedView extends AbstractView {

	private String feedType;
	private String feedContentType = "application/xml";

	@Autowired
	private FeedConfig feedConfig;

	private Map<String, String> feedUrls;

	public FeedView() {
		feedUrls = new HashMap<String, String>();
		feedUrls.put("rss_2.0", "rss");
		feedUrls.put("atom_1.0", "atom");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SyndFeed feed = new SyndFeedImpl();
		buildRssDocument(model, feed, request, response);

		response.setContentType(getFeedContentType() + "; charset=UTF-8");

		SyndFeedOutput out = new SyndFeedOutput();
		out.output(feed, response.getWriter());
	}

	private String getFeedLinkBase(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

	private String getFeedLink(Map<String, Object> model, HttpServletRequest request) {
		return getFeedLinkBase(request) + "/spring/products." + feedUrls.get(feedType);
	}

	private List<SyndPerson> getFeedAuthors(Map<String, Object> model, HttpServletRequest request) {
		SyndPerson feedAuthor = new SyndPersonImpl();

		feedAuthor.setName(feedConfig.getAuthorName());
		feedAuthor.setUri(getFeedLinkBase(request));
		feedAuthor.setEmail(feedConfig.getAuthorEmail());

		List<SyndPerson> ret = new ArrayList<SyndPerson>();
		ret.add(feedAuthor);

		return ret;
	}

	private SyndImage getFeedImage(Map<String, Object> model, HttpServletRequest request) {
		SyndImage ret = new SyndImageImpl();
		ret.setUrl(getFeedLinkBase(request) + feedConfig.getFeedImageUrl());
		ret.setLink(getFeedLink(model, request));
		ret.setDescription(feedConfig.getFeedDescription(model));
		ret.setTitle(feedConfig.getFeedDescription(model));

		return ret;
	}

	@SuppressWarnings("unchecked")
	protected void buildRssDocument(Map<String, Object> model, SyndFeed feed, HttpServletRequest request, HttpServletResponse response) throws Exception {
		feed.setFeedType(feedType);

		List<SyndEntry> entries = new ArrayList<SyndEntry>();
		SyndEntry entry;
		SyndContent description;

		List<Product> productList = (List<Product>) model.get(ProductListController.KEY_PRODUCT_LIST);

		feed.setTitle(feedConfig.getFeedTitle());
		feed.setLink(getFeedLink(model, request));
		feed.setDescription(feedConfig.getFeedDescription(model));
		feed.setAuthors(getFeedAuthors(model, request));
		feed.setImage(getFeedImage(model, request));
		feed.setPublishedDate(Calendar.getInstance().getTime());

		if (null != productList) {
			for (Product product : productList) {
				if (null != product) {
					description = new SyndContentImpl();
					description.setType("text/html");
					description.setValue(product.getDescription());

					entry = new SyndEntryImpl();
					entry.setTitle(product.getName() + "(" + product.getCompany() + ")");
					entry.setAuthor(feedConfig.getAuthorName());
					entry.setUpdatedDate(product.getEntryDate());
					entry.setPublishedDate(product.getEntryDate());
					entry.setLink(getProductPermalink(model, request, product));
					entry.setUri(getProductPermalink(model, request, product));
					entry.setDescription(description);

					entries.add(entry);
				}
			}
		}

		feed.setEntries(entries);
	}

	private String getProductPermalink(Map<String, Object> model, HttpServletRequest request, Product product) {
		return getFeedLinkBase(request) + "/spring/product?id=" + product.getId();
	}

	public String getFeedType() {
		return feedType;
	}

	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}

	public void setFeedContentType(String feedContentType) {
		this.feedContentType = feedContentType;
	}

	public String getFeedContentType() {
		return feedContentType;
	}

	public FeedConfig getFeedConfig() {
		return feedConfig;
	}

	public void setFeedConfig(FeedConfig feedConfig) {
		this.feedConfig = feedConfig;
	}

}
