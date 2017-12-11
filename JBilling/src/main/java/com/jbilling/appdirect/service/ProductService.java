package com.jbilling.appdirect.service;

import java.util.List;

import com.jbilling.appdirect.domain.entity.ProductEntity;
import com.jbilling.appdirect.domain.request.Product;
import com.jbilling.appdirect.exception.JBillingException;

public interface ProductService {

	public ProductEntity saveProduct(Product product) throws JBillingException;

	public ProductEntity updateProduct(ProductEntity product) throws JBillingException;
	
    public int delete(String productId) throws JBillingException;
     
    public ProductEntity getProduct(String productId) throws JBillingException;;
     
    public List<ProductEntity> list();
    
}
