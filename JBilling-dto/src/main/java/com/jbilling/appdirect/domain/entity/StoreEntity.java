package com.jbilling.appdirect.domain.entity;

import java.sql.Timestamp;

import com.jbilling.appdirect.domain.request.Store;

public class StoreEntity extends Store {

	private String storeId;
	private Timestamp createdTimeStamp;

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Timestamp getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}
}
