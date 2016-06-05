package com.btg.weather.exception;

public class WeatherServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	
	public WeatherServiceException(String errorCode) {
		this.errorCode = errorCode;		
	}
	
	public WeatherServiceException(String errorCode, Exception cause) {
		super(cause);
		this.errorCode = errorCode;		
	}
	
	public WeatherServiceException(String errorCode, String message, Exception cause) {
		super(message, cause);
		this.errorCode = errorCode;	
	}
	
	public String getErrorCode() {
		return errorCode;
	}
}
