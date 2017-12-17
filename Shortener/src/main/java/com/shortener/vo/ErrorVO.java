package com.shortener.vo;

public class ErrorVO implements ResultVO {
	public static final ErrorVO URL_NOT_FOUND = new ErrorVO("002", "SHORTENED URL NOT FOUND");
	
	private final String err_code;
	private final String description;
	
	public ErrorVO(String errorCode, String description) {
		this.err_code = errorCode;
		this.description = description;
	}
	
	public String getErr_code() {
		return this.err_code;
	}
	
	public String getDescription() {
		return this.description;
	}
}
