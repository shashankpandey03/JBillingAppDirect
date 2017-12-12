package com.jbilling.appdirect.converter;

import java.sql.Timestamp;
import java.util.UUID;

import com.jbilling.appdirect.domain.entity.ProductEntity;
import com.jbilling.appdirect.domain.request.Product;

/**
 * 
 * @author ShashankPandey
 * Class for converting Product related pojos
 * 
 */
public class ProductConverter {

	/**
	 * Converts Product to ProductEntity
	 * Add a UUID and timestamp to product entity
	 * @param p
	 * @return
	 */
	public static ProductEntity convertProductToEntity(Product p) {
		ProductEntity pe = new ProductEntity();
		pe.setProductId(UUID.randomUUID().toString());
		pe.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
		pe.setDescription(p.getDescription());
		pe.setPrice(p.getPrice());
		pe.setProductName(p.getProductName());
		return pe;
	}
}
