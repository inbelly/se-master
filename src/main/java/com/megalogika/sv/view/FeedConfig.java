package com.megalogika.sv.view;

import java.util.Map;

public class FeedConfig {

	private String feedTitle = "inbelly.co.uk";
	private String feedDescription = "List of products on inbelly.co.uk";
	private String authorName = "inbelly.co.uk";
	private String authorEmail = "info@inbelly.co.uk";
	private String feedImageUrl = "/img/logo2.png";

	String getFeedDescription(Map<String, Object> model) {
		return feedDescription;
	}

	public String getAuthorName() {
		return authorName;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public String getFeedTitle() {
		return feedTitle;
	}

	public String getFeedImageUrl() {
		return feedImageUrl;
	}

	public String getFeedDescription() {
		return feedDescription;
	}

	public void setFeedDescription(String feedDescription) {
		this.feedDescription = feedDescription;
	}

	public void setFeedTitle(String feedTitle) {
		this.feedTitle = feedTitle;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public void setFeedImageUrl(String feedImageUrl) {
		this.feedImageUrl = feedImageUrl;
	}

}
