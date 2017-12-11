package com.jbilling.appdirect.rest;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbilling.appdirect.constants.JBillingConstants;
import com.jbilling.appdirect.domain.entity.ProductEntity;
import com.jbilling.appdirect.domain.request.Product;
import com.jbilling.appdirect.domain.response.JBillingResponse;
import com.jbilling.appdirect.exception.JBillingException;
import com.jbilling.appdirect.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductRestApi {

	@Autowired
	private ProductService productService;
	
	
	@GetMapping
	public ResponseEntity<JBillingResponse> getAllProducts() {
		JBillingResponse response = createResponse(productService.list());
		return new ResponseEntity<JBillingResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping(value="/{productId}")
	public ResponseEntity<JBillingResponse> getProducts(@NotEmpty @PathVariable("productId") String productId) throws JBillingException {
		JBillingResponse response = createResponse(productService.getProduct(productId));
		return new ResponseEntity<JBillingResponse>(response,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<JBillingResponse> createProducts(@Valid @RequestBody Product product) throws JBillingException {
		JBillingResponse response = createResponse(productService.saveProduct(product));
		return new ResponseEntity<JBillingResponse>(response,HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<JBillingResponse> updateProducts(@Valid @RequestBody ProductEntity product) throws JBillingException {
		JBillingResponse response = createResponse(productService.updateProduct(product));
		return new ResponseEntity<JBillingResponse>(response,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{productId}")
	public ResponseEntity<JBillingResponse> deleteProducts(@NotEmpty @PathVariable("productId") String productId) throws JBillingException {
		productService.delete(productId);
		JBillingResponse response = createResponse(null);
		return new ResponseEntity<JBillingResponse>(response,HttpStatus.OK);
	}
	
	private JBillingResponse createResponse(Object data) {
		JBillingResponse response = new JBillingResponse();
		response.setMessage(JBillingConstants.SUCCESS);
		response.setPayload(data);
		response.setStatus(HttpStatus.OK.value());
		return response;
	}
}
