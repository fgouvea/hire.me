package com.shortener.vo;

public class ShortenUrlVO implements ResultVO {
	private String alias;
	private String url;
	private StatisticsVO statistics;
	
	public ShortenUrlVO() { }
	
	public ShortenUrlVO(String alias, String url, StatisticsVO statistics) {
		this.alias = alias;
		this.url = url;
		this.statistics = statistics;
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
	
	public StatisticsVO getStatistics() {
		return this.statistics;
	}
	
	public void setStatistics(StatisticsVO statistics) {
		this.statistics = statistics;
	}
}
