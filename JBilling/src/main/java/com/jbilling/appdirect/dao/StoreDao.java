package com.jbilling.appdirect.dao;

import java.util.List;

import com.jbilling.appdirect.domain.entity.StoreEntity;
import com.jbilling.appdirect.exception.JBillingResourceNotFoundException;

public interface StoreDao {

	public StoreEntity saveStore(StoreEntity store) throws JBillingResourceNotFoundException;

	public StoreEntity updateStore(StoreEntity store) throws JBillingResourceNotFoundException;
	
    public int delete(String storeId);
     
    public StoreEntity getStore(String storeId);
     
    public List<StoreEntity> list();
}
