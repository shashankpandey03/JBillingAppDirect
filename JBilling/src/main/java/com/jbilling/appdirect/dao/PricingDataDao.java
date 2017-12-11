package com.jbilling.appdirect.dao;

import java.util.List;

import com.jbilling.appdirect.domain.entity.PricingDataEntity;
import com.jbilling.appdirect.domain.response.ProductPricingDetails;
import com.jbilling.appdirect.exception.JBillingException;

public interface PricingDataDao {

	public PricingDataEntity savePricingData(PricingDataEntity pricingData) throws JBillingException;
	
    public int deleteByProductId(String productId);
     
    public int deleteByStoreId(String storeId);
    
    public List<PricingDataEntity> getPricingData(String productId);
    
    public List<ProductPricingDetails> getPricingProductDetails();
    
    public List<ProductPricingDetails> getPricingProductDetails(String productId);
}
