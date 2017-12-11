package com.jbilling.appdirect.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbilling.appdirect.domain.request.PricingData;
import com.jbilling.appdirect.domain.response.JBillingResponse;
import com.jbilling.appdirect.exception.JBillingException;
import com.jbilling.appdirect.service.PricingDataService;

@RestController
@RequestMapping("store/product")
public class ProductPricingRestApi {

	@Autowired
	private PricingDataService pricingDataService;
	
	@PostMapping
	public ResponseEntity<JBillingResponse> updateProductPricing(@Valid @RequestBody PricingData pricingData) throws JBillingException {
		JBillingResponse response = new JBillingResponse();
		response.setPayload(pricingDataService.savePricingData(pricingData));
		response.setMessage("Success");
		response.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<JBillingResponse>(response,HttpStatus.OK);
	}
}
