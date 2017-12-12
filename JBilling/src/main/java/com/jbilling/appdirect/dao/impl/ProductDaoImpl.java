package com.jbilling.appdirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jbilling.appdirect.constants.JBillingConstants;
import com.jbilling.appdirect.dao.PricingDataDao;
import com.jbilling.appdirect.dao.ProductDao;
import com.jbilling.appdirect.domain.entity.ProductEntity;
import com.jbilling.appdirect.exception.JBillingException;

/**
 * 
 * @author ShashankPandey
 * Dao class for db operations on product table
 *
 */
@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private JdbcTemplate template;

	@Autowired
	private PricingDataDao pricingDataDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ProductEntity saveProduct(ProductEntity product) throws JBillingException {
		int count = template.update(JBillingConstants.INSERT_PRODUCT, product.getProductId(), product.getProductName(),
				product.getPrice(), product.getDescription(),product.getCreatedTimeStamp());
		if(count != 0) {
			return product;
		} else {
			throw new JBillingException(HttpStatus.BAD_REQUEST, "Unable to save product");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ProductEntity updateProduct(ProductEntity product) throws JBillingException {
		int count = template.update(JBillingConstants.UPDATE_PRODUCT, product.getProductName(), product.getPrice(),product.getDescription(),
				new Timestamp(System.currentTimeMillis()), product.getProductId());
		if(count != 0) {
			return product;
		} else {
			throw new JBillingException(HttpStatus.BAD_REQUEST, "Unable to save product with product id :" + product.getProductId());
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int delete(String productId) {
		pricingDataDao.deleteByProductId(productId);
		int count = template.update(JBillingConstants.DELETE_PRODUCT, productId);
		return count;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ProductEntity getProduct(String productId) {
		ProductEntity product = template.query(JBillingConstants.GET_PRODUCT, new Object[] { productId },
				new ResultSetExtractor<ProductEntity>() {
					@Override
					public ProductEntity extractData(ResultSet rs) throws SQLException, DataAccessException {
						if (rs.next()) {
							ProductEntity p = new ProductEntity();
							p.setPrice(rs.getInt("price"));
							p.setProductId(rs.getString("product_id"));
							p.setProductName(rs.getString("product_name"));
							p.setDescription(rs.getString("description"));
							p.setCreatedTimeStamp(rs.getTimestamp("create_timestamp"));
							return p;
						}
						return null;
					}
				});
		return product;
	}

	@Override
	public List<ProductEntity> list() {
		List<ProductEntity> listProduct = template.query(JBillingConstants.LIST_PRODUCT, new RowMapper<ProductEntity>() {
			@Override
			public ProductEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductEntity p = new ProductEntity();
				p.setPrice(rs.getInt("price"));
				p.setProductId(rs.getString("product_id"));
				p.setProductName(rs.getString("product_name"));
				p.setDescription(rs.getString("description"));
				p.setCreatedTimeStamp(rs.getTimestamp("create_timestamp"));
				return p;
			}
		});
		return listProduct;
	}

}
