package com.jbilling.appdirect.dao;

import java.util.List;

import com.jbilling.appdirect.domain.entity.ProductEntity;
import com.jbilling.appdirect.exception.JBillingException;

public interface ProductDao {

	public ProductEntity saveProduct(ProductEntity product) throws JBillingException;

	public ProductEntity updateProduct(ProductEntity product) throws JBillingException;
	
    public int delete(String productId);
     
    public ProductEntity getProduct(String productId);
     
    public List<ProductEntity> list();
}
