package com.jbilling.appdirect.domain.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PricingData {
	
	@NotEmpty
	@JsonProperty("store")
	private String storeId;
	
	@NotEmpty
	@JsonProperty("product")
	private String productId;
	
	@NotNull
	@JsonProperty("price")
	private Integer price;

	@JsonProperty("notes")
	private String notes;
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}
