package com.jbilling.appdirect.rest;

import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbilling.appdirect.domain.response.JBillingResponse;
import com.jbilling.appdirect.domain.response.ProductPricingDetails;
import com.jbilling.appdirect.service.IdealPriceCalculatorService;

@RestController
@RequestMapping("/product")
public class ProductPricingDetailsRestApi {
	
	@Autowired
	private IdealPriceCalculatorService idealPriceCalculatorService;
	
	@GetMapping(value="/{productId}/prices")
	public ResponseEntity<JBillingResponse> getProductPricingDetails(@NotEmpty @PathVariable("productId") String productId) throws Exception {
		Map<ProductPricingDetails,List<Integer>> map = idealPriceCalculatorService.calculateIdealPrice(productId);
		JBillingResponse response = new JBillingResponse();
		response.setPayload(map.keySet());
		response.setStatus(HttpStatus.OK.value());
		response.setMessage("Success");
		return new ResponseEntity<JBillingResponse>(response,HttpStatus.OK);
	}
}
