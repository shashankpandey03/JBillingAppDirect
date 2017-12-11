package com.jbilling.appdirect.service;

import java.util.List;

import com.jbilling.appdirect.domain.entity.StoreEntity;
import com.jbilling.appdirect.domain.request.Store;
import com.jbilling.appdirect.exception.JBillingResourceNotFoundException;

public interface StoreService {

	public StoreEntity saveStore(Store store) throws JBillingResourceNotFoundException;

	public StoreEntity updateStore(StoreEntity store) throws JBillingResourceNotFoundException;
	
    public int delete(String storeId) throws JBillingResourceNotFoundException;
     
    public StoreEntity getStore(String storeId) throws JBillingResourceNotFoundException;;
     
    public List<StoreEntity> list();
    
}
