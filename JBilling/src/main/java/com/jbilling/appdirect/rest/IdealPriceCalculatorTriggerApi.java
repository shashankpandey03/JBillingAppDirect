package com.jbilling.appdirect.rest;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbilling.appdirect.domain.response.JBillingResponse;
import com.jbilling.appdirect.domain.response.JobResponse;
import com.jbilling.appdirect.exception.JBillingException;
import com.jbilling.appdirect.service.IdealPriceCalculatorService;

/**
 * 
 * @author ShashankPandey
 * Rest API for invoking ideal price calculator scheduler
 *
 */
@RestController
@RequestMapping("/jobs/pricecalculator")
public class IdealPriceCalculatorTriggerApi {

	@Autowired
	private IdealPriceCalculatorService idealPriceCalculatorService;
	
	@PostMapping
	public ResponseEntity<JBillingResponse> triggerIdealPRiceCalculatorJob(@RequestParam("command")String command) throws JBillingException {
		try {
			if("start".equals(command)) {
				idealPriceCalculatorService.calculateIdealPrice();
				JBillingResponse response = new JBillingResponse();
				response.setMessage("Success");
				response.setPayload(createJobStartedResponse());
				response.setStatus(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<JBillingResponse>(response,HttpStatus.OK);
			} else {
				throw new JBillingException(HttpStatus.BAD_REQUEST,"Invalid command provided");
			}
		} catch(Exception e) {
			throw new JBillingException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	private JobResponse createJobStartedResponse() {
		JobResponse response = new JobResponse();
		response.setJobName(UUID.randomUUID().toString());
		response.setStartedDate(new Date());
		return response;
	}
}
