package com.jbilling.appdirect.domain.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

	@NotEmpty
	@JsonProperty("name")
	private String productName;

	@NotEmpty
	@JsonProperty("description")
	private String description;

	@NotNull
	@JsonProperty("basePrice")
	private Integer price;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Integer getPrice() {
		return this.price;
	}
}
