package com.jbilling.appdirect.converter;

import java.sql.Timestamp;
import java.util.UUID;

import com.jbilling.appdirect.domain.entity.StoreEntity;
import com.jbilling.appdirect.domain.request.Store;

/**
 * 
 * @author ShashankPandey
 * Class for converting Store related pojos
 * 
 */
public class StoreConverter {

	/**
	 * Converts Store to StoreEntity
	 * Add a UUID and timestamp to product entity
	 * @param p
	 * @return
	 */
	public static StoreEntity convertStoreToEntity(Store p) {
		StoreEntity pe = new StoreEntity();
		pe.setStoreId(UUID.randomUUID().toString());
		pe.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
		pe.setDescription(p.getDescription());
		pe.setStoreName(p.getStoreName());
		return pe;
	}
}
