package com.jbilling.appdirect.domain.response;

import com.jbilling.appdirect.domain.entity.ProductEntity;

public class ProductPricingDetails extends ProductEntity {

	private String storeId;
	private Integer averageStorePrice;
	private Integer lowestStorePrice;
	private Integer highestStorePrice;
	private Integer idealPrice;
	private Integer noOfPrices;

	
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Integer getAverageStorePrice() {
		return averageStorePrice;
	}

	public void setAverageStorePrice(Integer averageStorePrice) {
		this.averageStorePrice = averageStorePrice;
	}

	public Integer getLowestStorePrice() {
		return lowestStorePrice;
	}

	public void setLowestStorePrice(Integer lowestStorePrice) {
		this.lowestStorePrice = lowestStorePrice;
	}

	public Integer getHighestStorePrice() {
		return highestStorePrice;
	}

	public void setHighestStorePrice(Integer highestStorePrice) {
		this.highestStorePrice = highestStorePrice;
	}

	public Integer getIdealPrice() {
		return idealPrice;
	}

	public void setIdealPrice(Integer idealPrice) {
		this.idealPrice = idealPrice;
	}

	public Integer getNoOfPrices() {
		return noOfPrices;
	}

	public void setNoOfPrices(Integer noOfPrices) {
		this.noOfPrices = noOfPrices;
	}

	

	@Override
	public int hashCode() {
		int result = super.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductPricingDetails other = (ProductPricingDetails) obj;
		if (getProductId() == null) {
			if (other.getProductId() != null)
				return false;
		} else if (!getProductId().equals(other.getProductId()))
			return false;
		return true;
	}
}
