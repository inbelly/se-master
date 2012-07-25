package com.megalogika.sv.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.webflow.mvc.servlet.MvcExternalContext;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.fetcher.FeedFetcher;
import com.sun.syndication.fetcher.FetcherException;
import com.sun.syndication.fetcher.impl.FeedFetcherCache;
import com.sun.syndication.fetcher.impl.HashMapFeedInfoCache;
import com.sun.syndication.fetcher.impl.HttpURLFeedFetcher;
import com.sun.syndication.io.FeedException;

public class BlogReader  {
	protected transient Logger logger = Logger.getLogger(BlogReader.class);

	private FeedFetcherCache feedInfoCache = HashMapFeedInfoCache.getInstance();
	private String blogUrl = "";

	public SyndFeed getSyndFeed() {
		synchronized (feedInfoCache) {
			try {
				return feedInfoCache.getFeedInfo(getFeedUrl()).getSyndFeed();
			} catch (MalformedURLException e) {
				logger.error("Could not get SyndFeed from cache: ", e);
				return null;
			} catch (NullPointerException e) {
				logger.error("Could not get SyndFeed from cache, perhaps FeedInfo is still null, blog is not updated");
				return null;
			}
		}
	}

	public URL getFeedUrl() throws MalformedURLException {
		return new URL(getBlogUrl());
	}

	public SyndFeed fixBlogEntryLinks(SyndFeed feed, HttpServletRequest request) {
		try {
			if (null != request) {
				Boolean zebra = (Boolean) request.getAttribute("ZEBRA");
				if (null != zebra && zebra) {
					if (null != feed) {
						SyndFeed ret = (SyndFeed) feed.clone();
						for (Iterator<SyndEntry> entries = ret.getEntries().iterator(); entries.hasNext();) {
							SyndEntry entry = entries.next();
							String link = entry.getLink();
							link = link.replace("blog.sveikasvaikas.lt", "blog.sveikasvaikas.zebra.lt");
							entry.setLink(link);
						}
						
						return ret;
					}
				}
			}
		} catch (Exception e) {
			logger.error("WHILE UPDATING LINKS: ", e);
		}
		
		return feed;
	}
	
	
	public SyndFeed fixBlogEntryLinks(SyndFeed feed, MvcExternalContext requestContext) {
		try {
			if (null != requestContext) {
				return fixBlogEntryLinks(feed, ((HttpServletRequest)requestContext.getNativeRequest()));
			}
		} catch (Exception e) {
			logger.error("WHILE UPDATING LINKS: ", e);
		}
		
		return feed;
	}
	
	public SyndFeed read() {
		synchronized (feedInfoCache) {
			FeedFetcher feedFetcher = new HttpURLFeedFetcher(feedInfoCache);
			try {
				SyndFeed ret = feedFetcher.retrieveFeed(new URL(getBlogUrl()));
				return ret;
			} catch (IllegalArgumentException e) {
				logger.error("Could not fetch feed for url: '" + getBlogUrl() + "': ", e);
			} catch (MalformedURLException e) {
				logger.error("Could not fetch feed for url: '" + getBlogUrl() + "': ", e);
			} catch (IOException e) {
				logger.error("Could not fetch feed for url: '" + getBlogUrl() + "': ", e);
			} catch (FeedException e) {
				logger.error("Could not fetch feed for url: '" + getBlogUrl() + "': ", e);
			} catch (FetcherException e) {
				logger.error("Could not fetch feed for url: '" + getBlogUrl() + "': ", e);
			}
			return null;
		}
	}

	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}

	public String getBlogUrl() {
		return blogUrl;
	}
}
