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
import com.jbilling.appdirect.domain.entity.StoreEntity;
import com.jbilling.appdirect.domain.request.Store;
import com.jbilling.appdirect.domain.response.JBillingResponse;
import com.jbilling.appdirect.exception.JBillingException;
import com.jbilling.appdirect.service.StoreService;

/**
 * 
 * @author ShashankPandey
 * Rest API for Store related operations
 *
 */

@RestController
@RequestMapping("store")
public class StoreRestApi {

	@Autowired
	private StoreService storeService;
	
	/**
	 * API to get all the stores
	 * @return
	 */
	@GetMapping
	public ResponseEntity<JBillingResponse> getAllStores() {
		JBillingResponse response = createResponse(storeService.list());
		return new ResponseEntity<JBillingResponse>(response,HttpStatus.OK);
	}
	
	/**
	 * API to fetch a specific store by storeId
	 * @param storeId
	 * @return
	 * @throws JBillingException
	 */
	@GetMapping(value="/{storeId}")
	public ResponseEntity<JBillingResponse> getStores(@NotEmpty @PathVariable("storeId") String storeId) throws JBillingException {
		JBillingResponse response = createResponse(storeService.getStore(storeId));
		return new ResponseEntity<JBillingResponse>(response,HttpStatus.OK);
	}
	
	/**
	 * API to save store
	 * @param store
	 * @return
	 * @throws JBillingException
	 */
	@PostMapping
	public ResponseEntity<JBillingResponse> createStores(@Valid @RequestBody Store store) throws JBillingException {
		JBillingResponse response = createResponse(storeService.saveStore(store));
		return new ResponseEntity<JBillingResponse>(response,HttpStatus.OK);
	}
	
	/**
	 * API to update a specific store
	 * @param store
	 * @return
	 * @throws JBillingException
	 */
	@PutMapping
	public ResponseEntity<JBillingResponse> updateStores(@Valid @RequestBody StoreEntity store) throws JBillingException {
		JBillingResponse response = createResponse(storeService.updateStore(store));
		return new ResponseEntity<JBillingResponse>(response,HttpStatus.OK);
	}
	
	/**
	 * API to delete a specific store by storeId
	 * @param storeId
	 * @return
	 * @throws JBillingException
	 */
	@DeleteMapping(value="/{storeId}")
	public ResponseEntity<JBillingResponse> deleteStores(@NotEmpty @PathVariable("storeId") String storeId) throws JBillingException {
		storeService.delete(storeId);
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
