package com.jbilling.appdirect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jbilling.appdirect.converter.ProductConverter;
import com.jbilling.appdirect.dao.ProductDao;
import com.jbilling.appdirect.domain.entity.ProductEntity;
import com.jbilling.appdirect.domain.request.Product;
import com.jbilling.appdirect.exception.JBillingException;
import com.jbilling.appdirect.exception.JBillingResourceNotFoundException;
import com.jbilling.appdirect.service.ProductService;

/**
 * Service class for product related operations
 * @author ShashankPandey
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public ProductEntity saveProduct(Product product) throws JBillingException {
		return productDao.saveProduct(ProductConverter.convertProductToEntity(product));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public ProductEntity updateProduct(ProductEntity product) throws JBillingException {
		return productDao.updateProduct(product);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int delete(String productId) throws JBillingException {
		int count = productDao.delete(productId);
		if(count == 0) {
			throw new JBillingResourceNotFoundException(HttpStatus.NOT_FOUND,"Product not found with id " + productId);
		}
		return count;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ProductEntity getProduct(String productId) throws JBillingException {
		ProductEntity product = productDao.getProduct(productId);
		if(product == null) {
			throw new JBillingResourceNotFoundException(HttpStatus.NOT_FOUND,"Product not found with id " + productId);
		}
		return product;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ProductEntity> list() {
		return productDao.list();
	}
}
