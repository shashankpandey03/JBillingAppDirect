package com.jbilling.appdirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jbilling.appdirect.constants.JBillingConstants;
import com.jbilling.appdirect.dao.PricingDataDao;
import com.jbilling.appdirect.domain.entity.PricingDataEntity;
import com.jbilling.appdirect.domain.response.ProductPricingDetails;
import com.jbilling.appdirect.exception.JBillingException;

/**
 * 
 * @author ShashankPandey
 * Dao class for db operations on pricing table
 *
 */
@Repository
public class PricingDataDaoImpl implements PricingDataDao {

	@Autowired
	private JdbcTemplate template;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public PricingDataEntity savePricingData(PricingDataEntity pricingData) throws JBillingException {
		int count = template.update(JBillingConstants.INSERT_PRICING_DATA, pricingData.getPricingDataId(),
				pricingData.getStoreId(), pricingData.getProductId(), pricingData.getPrice(),pricingData.getNotes(),
				pricingData.getCreatedTimeStamp());
		if(count != 0) {
			return pricingData;
		} else {
			throw new JBillingException(HttpStatus.BAD_REQUEST,"Could not save pricing data");
		}
	}

	@Override
	public int deleteByProductId(String productId) {
		return template.update(JBillingConstants.DELETE_PRICING_DATA_BY_PRODUCTID, productId);
	}

	@Override
	public int deleteByStoreId(String storeId) {
		return template.update(JBillingConstants.DELETE_PRICING_DATA_BY_STOREID, storeId);
	}

	@Override
	public List<PricingDataEntity> getPricingData(String productId) {
		
		List<PricingDataEntity> listProduct = template.query(JBillingConstants.GET_PRICING_DATA_BY_PRODUCTID, new RowMapper<PricingDataEntity>() {
			@Override
			public PricingDataEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
				PricingDataEntity p = new PricingDataEntity();
				p.setPricingDataId(rs.getString("pricing_id"));
				p.setPrice(rs.getInt("price"));
				p.setProductId(rs.getString("product_id"));
				p.setStoreId(rs.getString("store_id"));
				return p;
			}
		});
		return listProduct;
	}

	@Override
	public List<ProductPricingDetails> getPricingProductDetails() {
		List<ProductPricingDetails> listProduct = template.query(JBillingConstants.GET_PRODUCT_DETAILS, new RowMapper<ProductPricingDetails>() {
			@Override
			public ProductPricingDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
			
				ProductPricingDetails p = new ProductPricingDetails();
				p.setProductName(rs.getString("product_name"));
				p.setPrice(new Integer(rs.getInt("base_price")));
				p.setProductId(rs.getString("product_id"));
				p.setHighestStorePrice(rs.getInt("max_price"));
				p.setLowestStorePrice(rs.getInt("min_price"));
				p.setAverageStorePrice(rs.getInt("avg_price"));
				p.setNoOfPrices(rs.getInt("noOfPrices"));
				p.setDescription(rs.getString("description"));
				return p;
			}
		});
		return listProduct;
	}

	@Override
	public List<ProductPricingDetails> getPricingProductDetails(String productId) {
		
		String query = JBillingConstants.GET_PRODUCT_DETAILS + " where p.product_id = ?";
		
		List<ProductPricingDetails> listProduct = template.query(query, new Object[]{productId} ,new RowMapper<ProductPricingDetails>() {
			@Override
			public ProductPricingDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductPricingDetails p = new ProductPricingDetails();
				p.setProductName(rs.getString("product_name"));
				p.setPrice(rs.getInt("base_price"));
				p.setProductId(rs.getString("product_id"));
				p.setHighestStorePrice(rs.getInt("max_price"));
				p.setLowestStorePrice(rs.getInt("min_price"));
				p.setAverageStorePrice(rs.getInt("avg_price"));
				p.setNoOfPrices(rs.getInt("noOfPrices"));
				p.setDescription(rs.getString("description"));
				return p;
			}
		});
		return listProduct;
	}
}
