package com.jbilling.appdirect.domain.entity;

import java.sql.Timestamp;

import com.jbilling.appdirect.domain.request.Product;

public class ProductEntity extends Product {

	private String productId;
	private Timestamp createdTimeStamp;

	public Timestamp getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
}
