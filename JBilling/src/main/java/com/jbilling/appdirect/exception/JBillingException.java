package com.jbilling.appdirect.exception;

import org.springframework.http.HttpStatus;

public class JBillingException extends Exception {
	
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	public JBillingException() {
		super();
		httpStatus = HttpStatus.BAD_REQUEST;
	}
	
	public JBillingException(HttpStatus status, String message) {
		super(message);
		httpStatus = status;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
