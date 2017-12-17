package com.shortener.vo;

public class ShortenUrlVO implements ResultVO {
	private String alias;
	private String url;
	
	public ShortenUrlVO() { }
	
	public ShortenUrlVO(String alias, String url) {
		this.alias = alias;
		this.url = url;
	}
	
	public String getAlias() {
		return this.alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
