package com.jbilling.appdirect.converter;

import java.sql.Timestamp;
import java.util.UUID;

import com.jbilling.appdirect.domain.entity.ProductEntity;
import com.jbilling.appdirect.domain.request.Product;

public class ProductConverter {

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
