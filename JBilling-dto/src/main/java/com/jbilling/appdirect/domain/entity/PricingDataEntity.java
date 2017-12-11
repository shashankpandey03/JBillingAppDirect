package com.jbilling.appdirect.domain.entity;

import java.sql.Timestamp;

import com.jbilling.appdirect.domain.request.PricingData;

public class PricingDataEntity extends PricingData {

	private String pricingDataId;
	private Timestamp createdTimeStamp;

	public Timestamp getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	public String getPricingDataId() {
		return pricingDataId;
	}

	public void setPricingDataId(String pricingDataId) {
		this.pricingDataId = pricingDataId;
	}
}
