package com.shortener.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;

@Entity
public class ShortUrl {
	
	@Column(columnDefinition = "VARBINARY(255)")
	@Id
	private String alias;
	
	@Column(nullable = false)
	private String url;
	
	private int views;
	
	public ShortUrl() {
		this.views = 0;
	}
	
	public ShortUrl(String alias, String url) {
		this.alias = alias;
		this.url = url;
		this.views = 0;
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
		return views;
	}
	
	public void setViews(int views) {
		this.views = views;
	}
	
	public void incrementViews() {
		this.views++;
	}
}
