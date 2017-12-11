package com.jbilling.appdirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.jbilling.appdirect.dao.StoreDao;
import com.jbilling.appdirect.domain.entity.StoreEntity;
import com.jbilling.appdirect.exception.JBillingResourceNotFoundException;

@Repository
public class StoreDaoImpl implements StoreDao {

	@Autowired
	private JdbcTemplate template;

	@Autowired
	private PricingDataDao pricingDataDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public StoreEntity saveStore(StoreEntity store) throws JBillingResourceNotFoundException {
		int count = template.update(JBillingConstants.INSERT_STORE, store.getStoreId(), store.getStoreName(),store.getDescription(),
				store.getCreatedTimeStamp());
		if(count != 0) {
			return store;
		} else {
			throw new JBillingResourceNotFoundException(HttpStatus.BAD_REQUEST,"Store not saved");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public StoreEntity updateStore(StoreEntity store) throws JBillingResourceNotFoundException {
		int count =  template.update(JBillingConstants.UPDATE_STORE, store.getStoreName(),store.getDescription(),
				store.getCreatedTimeStamp(), store.getStoreId());
		if(count != 0) {
			return store;
		} else {
			throw new JBillingResourceNotFoundException(HttpStatus.NOT_FOUND,"Store not found with id " + store.getStoreId());
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int delete(String storeId) {
		int count = template.update(JBillingConstants.DELETE_STORE, storeId);
		if(count != 0) {
			pricingDataDao.deleteByStoreId(storeId);
		}
		return count;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public StoreEntity getStore(String storeId) {
		StoreEntity store = template.query(JBillingConstants.GET_STORE, new Object[] { storeId },
				new ResultSetExtractor<StoreEntity>() {
					@Override
					public StoreEntity extractData(ResultSet rs) throws SQLException, DataAccessException {
						if (rs.next()) {
							StoreEntity p = new StoreEntity();
							p.setStoreId(rs.getString("store_id"));
							p.setStoreName(rs.getString("store_name"));
							p.setDescription(rs.getString("description"));
							p.setCreatedTimeStamp(rs.getTimestamp("create_timestamp"));
							return p;
						}
						return null;
					}
				});
		return store;
	}

	@Override
	public List<StoreEntity> list() {
		List<StoreEntity> listStore = template.query(JBillingConstants.LIST_STORE, new RowMapper<StoreEntity>() {
			@Override
			public StoreEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
				StoreEntity p = new StoreEntity();
				p.setStoreId(rs.getString("store_id"));
				p.setStoreName(rs.getString("store_name"));
				p.setDescription(rs.getString("description"));
				p.setCreatedTimeStamp(rs.getTimestamp("create_timestamp"));
				return p;
			}
		});
		return listStore;
	}

}
