package com.jbilling.appdirect.converter;

import java.sql.Timestamp;
import java.util.UUID;

import com.jbilling.appdirect.domain.entity.PricingDataEntity;
import com.jbilling.appdirect.domain.request.PricingData;

public class PricingDataConverter {

	public static PricingDataEntity convertPricingDataToEntity(PricingData p) {
		PricingDataEntity pe = new PricingDataEntity();
		pe.setPricingDataId(UUID.randomUUID().toString());
		pe.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
		pe.setNotes(p.getNotes());
		pe.setPrice(p.getPrice());
		pe.setStoreId(p.getStoreId());
		pe.setProductId(p.getProductId());
		return pe;
	}
}
