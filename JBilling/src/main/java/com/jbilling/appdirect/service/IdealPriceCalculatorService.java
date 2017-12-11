package com.jbilling.appdirect.service;

import java.util.List;
import java.util.Map;

import com.jbilling.appdirect.domain.response.ProductPricingDetails;

public interface IdealPriceCalculatorService {

	public void calculateIdealPrice() throws Exception ;
	public Map<ProductPricingDetails,List<Integer>> calculateIdealPrice(String productId) throws Exception ;
}
