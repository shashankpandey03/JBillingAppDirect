package com.jbilling.appdirect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jbilling.appdirect.converter.StoreConverter;
import com.jbilling.appdirect.dao.StoreDao;
import com.jbilling.appdirect.domain.entity.StoreEntity;
import com.jbilling.appdirect.domain.request.Store;
import com.jbilling.appdirect.exception.JBillingResourceNotFoundException;
import com.jbilling.appdirect.service.StoreService;

/**
 * Service class for store related operations
 * @author ShashankPandey
 *
 */
@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreDao storeDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public StoreEntity saveStore(Store store) throws JBillingResourceNotFoundException {
		return storeDao.saveStore(StoreConverter.convertStoreToEntity(store));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public StoreEntity updateStore(StoreEntity store) throws JBillingResourceNotFoundException {
		return storeDao.updateStore(store);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int delete(String storeId) throws JBillingResourceNotFoundException {
		int count = storeDao.delete(storeId);
		if(count == 0) {
			throw new JBillingResourceNotFoundException(HttpStatus.NOT_FOUND,"Store not found with id " + storeId);
		}
		return count;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public StoreEntity getStore(String storeId) throws JBillingResourceNotFoundException {
		StoreEntity store = storeDao.getStore(storeId);
		if(store == null) {
			throw new JBillingResourceNotFoundException(HttpStatus.NOT_FOUND,"Store not found with id " + storeId);
		}
		return store;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<StoreEntity> list() {
		return storeDao.list();
	}
}
