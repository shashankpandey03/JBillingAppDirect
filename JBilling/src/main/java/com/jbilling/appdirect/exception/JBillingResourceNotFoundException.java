package com.jbilling.appdirect.exception;

import org.springframework.http.HttpStatus;

public class JBillingResourceNotFoundException extends JBillingException {

	public JBillingResourceNotFoundException() {
		super();
	}
	
	public JBillingResourceNotFoundException(HttpStatus status, String message) {
		super(status,message);
	}
}
