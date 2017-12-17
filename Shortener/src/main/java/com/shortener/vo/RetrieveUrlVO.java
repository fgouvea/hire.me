package com.shortener.vo;

public class RetrieveUrlVO implements ResultVO {
	private final String url;
	
	public RetrieveUrlVO(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return this.url;
	}
}
