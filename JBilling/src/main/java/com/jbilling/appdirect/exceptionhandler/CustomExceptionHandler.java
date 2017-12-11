package com.jbilling.appdirect.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jbilling.appdirect.domain.response.JBillingResponse;
import com.jbilling.appdirect.exception.JBillingException;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<JBillingResponse> handle(Exception ex) {
		
		logger.error("An exception occurred. " + ex.getMessage());
		
		JBillingResponse responseMsg = new JBillingResponse(); 
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		if(ex instanceof JBillingException) {
			status = ((JBillingException)ex).getHttpStatus();
			responseMsg.setStatus(status.value());
			responseMsg.setMessage(ex.getMessage());
		} else {
			responseMsg.setStatus(status.value());
			responseMsg.setMessage("An error occurred. Please try again after sometime");
		}
		
		return new ResponseEntity<JBillingResponse>(responseMsg,status);
	}
}
