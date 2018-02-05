package com.wdb007.baseservice.utility;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "bookinfo_gather")
public class GatherBookConfig {
	public String getBook_coverpath() {
		return book_coverpath;
	}

	public void setBook_coverpath(String book_coverpath) {
		this.book_coverpath = book_coverpath;
	}

	public String url;
	public String image_path;
    public String book_coverpath;
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

}