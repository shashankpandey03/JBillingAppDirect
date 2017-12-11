package com.jbilling.appdirect.service;

import java.util.List;
import java.util.Map;

import com.jbilling.appdirect.domain.response.ProductPricingDetails;

public interface IRuleProcessorService {
	
	public void processRules(String jsonRule, Map<ProductPricingDetails,List<Integer>> map) throws Exception;
}
