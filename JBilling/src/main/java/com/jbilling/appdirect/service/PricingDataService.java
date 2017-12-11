package com.jbilling.appdirect.service;

import java.util.List;

import com.jbilling.appdirect.domain.entity.PricingDataEntity;
import com.jbilling.appdirect.domain.request.PricingData;
import com.jbilling.appdirect.domain.response.ProductPricingDetails;
import com.jbilling.appdirect.exception.JBillingException;

public interface PricingDataService {

	public PricingDataEntity savePricingData(PricingData pricingData) throws JBillingException;

	public List<ProductPricingDetails> getPricingProductDetails();

	public List<ProductPricingDetails> getPricingProductDetails(String productId);
}
