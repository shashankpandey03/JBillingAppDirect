package com.jbilling.appdirect.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbilling.appdirect.cache.JBillingCache;
import com.jbilling.appdirect.constants.JBillingConstants;
import com.jbilling.appdirect.domain.response.ProductPricingDetails;
import com.jbilling.appdirect.service.IRuleProcessorService;
import com.jbilling.appdirect.service.IdealPriceCalculatorService;
import com.jbilling.appdirect.service.PricingDataService;

/**
 * Service class to calculate ideal price
 * @author e070404
 *
 */
@Component
public class IdealPriceCalculatorServiceImpl implements IdealPriceCalculatorService {

	@Autowired
	private IRuleProcessorService ruleProcessorService;

	@Autowired
	private JBillingCache cache;
	
	@Autowired
	private PricingDataService pricingDataService;

	/**
	 * Method to calculate ideal price for all the productIds present
	 */
	@Override
	public void calculateIdealPrice() throws Exception {
		
		try {
			String json_rule = (String) cache.get(JBillingConstants.JSON_RULE);
			if (json_rule != null && !json_rule.isEmpty()) {
				
				List<ProductPricingDetails> pricingDetailsList = pricingDataService.getPricingProductDetails();
				Map<ProductPricingDetails,List<Integer>> map = getPricingDataMap(pricingDetailsList);
				ruleProcessorService.processRules(json_rule, map);
				updateCache(map);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Method to calculate ideal price for a specific product
	 * If ideal price is already present in cache , it fetches from there else,
	 * calculates it. 
	 * After calculation updates the cache.
	 */
	@Override
	public Map<ProductPricingDetails,List<Integer>> calculateIdealPrice(String productId) throws Exception {
		
		try {
			String json_rule = (String) cache.get(JBillingConstants.JSON_RULE);
			if (json_rule != null && !json_rule.isEmpty()) {

				List<ProductPricingDetails> pricingDetailsList = pricingDataService.getPricingProductDetails(productId);
				Map<ProductPricingDetails,List<Integer>> map = getPricingDataMap(pricingDetailsList);
				if(cache.get(productId) != null) {
					updateMapWithIdealPrice(map, (ProductPricingDetails) cache.get(productId));
				} else {
					ruleProcessorService.processRules(json_rule, map);
				}
				updateCache(map);
				return map;
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}
	
	/**
	 * Updates the pricingdetails map with respective ideal price
	 * @param map
	 * @param pricingDetails
	 */
	private void updateMapWithIdealPrice(Map<ProductPricingDetails,List<Integer>> map, ProductPricingDetails pricingDetails) {
		if(map != null && !map.isEmpty()) {
			for(Map.Entry<ProductPricingDetails,List<Integer>> details : map.entrySet()) {
				if(details.getKey().getProductId().equals(pricingDetails.getProductId())) {
					details.getKey().setIdealPrice(pricingDetails.getIdealPrice());
				}
			}
		}
	}
	
	private Map<ProductPricingDetails,List<Integer>> getPricingDataMap(List<ProductPricingDetails> pricingDetailsList) {
		if(pricingDetailsList != null && !pricingDetailsList.isEmpty()) {
			Map<ProductPricingDetails,List<Integer>> map = new HashMap<ProductPricingDetails,List<Integer>>();
			
			for(ProductPricingDetails productPricingDetails: pricingDetailsList) {
				if(map.containsKey(productPricingDetails)) {
					map.get(productPricingDetails).add(productPricingDetails.getPrice());
				} else {
					List<Integer> list = new ArrayList<Integer>();
					list.add(productPricingDetails.getPrice());
					map.put(productPricingDetails, list);
				}
			}
			return map;
		}
		return null;
	}
	
	/**
	 * Update cache with the ideal price for all the productIds
	 * @param map
	 */
	private void updateCache(Map<ProductPricingDetails,List<Integer>> map) {
		if(map != null && !map.isEmpty()) {
			for(Map.Entry<ProductPricingDetails,List<Integer>> entry : map.entrySet()) {
				cache.put(entry.getKey().getProductId(), entry.getKey());
			}
		}
	}
}
