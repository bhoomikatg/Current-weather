package com.btg.weather.exception;

public class WeatherServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ErrorCode errorCode;
	
	public WeatherServiceException(ErrorCode errorCode) {
		this.errorCode = errorCode;		
	}
	
	public WeatherServiceException(ErrorCode errorCode, Exception cause) {
		super(cause);
		this.errorCode = errorCode;		
	}
	
	public WeatherServiceException(ErrorCode errorCode, String message, Exception cause) {
		super(message, cause);
		this.errorCode = errorCode;	
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
