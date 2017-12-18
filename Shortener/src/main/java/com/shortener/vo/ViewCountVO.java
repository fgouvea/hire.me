package com.shortener.vo;

public class ViewCountVO implements ResultVO {
	private String alias;
	private String url;
	private int views;
	
	public ViewCountVO() { }
	
	public ViewCountVO(String alias, String url, int views) {
		this.alias = alias;
		this.url = url;
		this.views = views;
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
	
	public int getViews() {
		return this.views;
	}
	
	public void setViews(int views) {
		this.views = views;
	}
}
