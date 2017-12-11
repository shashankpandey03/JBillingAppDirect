package com.jbilling.appdirect.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbilling.appdirect.constants.JBillingTestConstants;
import com.jbilling.appdirect.domain.entity.PricingDataEntity;
import com.jbilling.appdirect.domain.entity.ProductEntity;
import com.jbilling.appdirect.domain.entity.StoreEntity;
import com.jbilling.appdirect.domain.request.PricingData;
import com.jbilling.appdirect.domain.request.Product;
import com.jbilling.appdirect.domain.request.Store;
import com.jbilling.appdirect.domain.response.ProductPricingDetails;

public class JBillingTestUtil {

	public static List<ProductEntity> getProductList() {
		List<ProductEntity> list = new ArrayList<ProductEntity>();
		list.add(getProductEntity());
		return list;
	}

	public static ProductEntity getProductEntity() {
		ProductEntity p = new ProductEntity();
		p.setProductId(JBillingTestConstants.PRODUCT_ID);
		p.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
		p.setDescription(JBillingTestConstants.PRODUCT_DESCRIPTION);
		return p;
	}

	public static Product getProduct() {
		Product p = new Product();
		p.setPrice(100);
		p.setProductName(JBillingTestConstants.PRODUCT_NAME);
		p.setDescription(JBillingTestConstants.PRODUCT_DESCRIPTION);
		return p;
	}

	public static String getProductJson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}
	
	public static PricingDataEntity getPricingDataEntity() {
		PricingDataEntity p = new PricingDataEntity();
		p.setPricingDataId(JBillingTestConstants.PRICING_ID);
		p.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
		p.setNotes(JBillingTestConstants.PRICING_DESCRIPTION);
		return p;
	}
	
	public static PricingData getPricingData() {
		PricingData p = new PricingData();
		p.setNotes(JBillingTestConstants.PRICING_DESCRIPTION);
		p.setPrice(100);
		p.setProductId(JBillingTestConstants.PRODUCT_ID);
		p.setStoreId(JBillingTestConstants.STORE_ID);
		return p;
	}
	
	public static List<StoreEntity> getStoreList() {
		List<StoreEntity> list = new ArrayList<StoreEntity>();
		list.add(getStoreEntity());
		return list;
	}

	public static StoreEntity getStoreEntity() {
		StoreEntity p = new StoreEntity();
		p.setStoreId(JBillingTestConstants.STORE_ID);
		p.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
		p.setDescription(JBillingTestConstants.STORE_DESCRIPTION);
		return p;
	}

	public static Store getStore() {
		Store p = new Store();
		p.setStoreName(JBillingTestConstants.STORE_NAME);
		p.setDescription(JBillingTestConstants.STORE_DESCRIPTION);
		return p;
	}
	
	public static ProductPricingDetails getProductPricingDetails() {
		ProductPricingDetails p = new ProductPricingDetails();
		p.setProductName(JBillingTestConstants.PRODUCT_NAME);
		p.setPrice(new Integer(100));
		p.setProductId(JBillingTestConstants.PRODUCT_ID);
		p.setHighestStorePrice(300);
		p.setLowestStorePrice(200);
		p.setAverageStorePrice(250);
		p.setNoOfPrices(2);
		p.setDescription(JBillingTestConstants.PRODUCT_DESCRIPTION);
		return p;
	}
	
	public static List<ProductPricingDetails> getProductPricingDetailsList() {
		List<ProductPricingDetails> list = new ArrayList<ProductPricingDetails>();
		list.add(getProductPricingDetails());
		return list;
	}

	public static String getStoreJson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}
}
