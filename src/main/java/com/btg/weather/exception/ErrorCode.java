package com.btg.weather.exception;

public enum ErrorCode {
	CITY_NOT_VALID(1),
	GENERIC_ERROR(2);
	public int errorCode;
	private ErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
