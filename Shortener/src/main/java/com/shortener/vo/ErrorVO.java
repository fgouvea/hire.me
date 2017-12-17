package com.shortener.vo;

public class ErrorVO implements ResultVO {
	public static final ErrorVO ALIAS_ALREADY_EXISTS = new ErrorVO("001", "CUSTOM ALIAS ALREADY EXISTS");
	public static final ErrorVO URL_NOT_FOUND 		 = new ErrorVO("002", "SHORTENED URL NOT FOUND");
	public static final ErrorVO NO_URL_GIVEN 		 = new ErrorVO("003", "NO URL GIVEN");
	
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
