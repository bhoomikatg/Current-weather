package com.btg.weather.exception;

public enum ErrorCode {
	CITY_NOT_VALID(1);
	int errorCode;
	private ErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
