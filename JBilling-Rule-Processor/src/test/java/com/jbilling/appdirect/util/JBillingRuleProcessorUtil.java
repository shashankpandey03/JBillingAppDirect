package com.jbilling.appdirect.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jbilling.appdirect.constants.JBillingRuleProcessorTestConstants;
import com.jbilling.appdirect.domain.response.ProductPricingDetails;

public class JBillingRuleProcessorUtil {
	
	public static ProductPricingDetails getProductPricingDetails() {
		ProductPricingDetails p = new ProductPricingDetails();
		p.setProductName(JBillingRuleProcessorTestConstants.PRODUCT_NAME);
		p.setPrice(new Integer(100));
		p.setProductId(JBillingRuleProcessorTestConstants.PRODUCT_ID);
		p.setHighestStorePrice(300);
		p.setLowestStorePrice(200);
		p.setAverageStorePrice(250);
		p.setNoOfPrices(2);
		p.setDescription(JBillingRuleProcessorTestConstants.PRODUCT_DESCRIPTION);
		return p;
	}
	
	public static List<ProductPricingDetails> getProductPricingDetailsList() {
		List<ProductPricingDetails> list = new ArrayList<ProductPricingDetails>();
		list.add(getProductPricingDetails());
		return list;
	}
	
	public static List<Integer> getPriceList() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(100);
		list.add(200);
		return list;
	}
}
