package com.jbilling.appdirect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jbilling.appdirect.converter.PricingDataConverter;
import com.jbilling.appdirect.dao.PricingDataDao;
import com.jbilling.appdirect.domain.entity.PricingDataEntity;
import com.jbilling.appdirect.domain.request.PricingData;
import com.jbilling.appdirect.domain.response.ProductPricingDetails;
import com.jbilling.appdirect.exception.JBillingException;
import com.jbilling.appdirect.service.PricingDataService;

@Service
public class PricingDataServiceImpl implements PricingDataService {

	@Autowired
	private PricingDataDao pricingDataDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public PricingDataEntity savePricingData(PricingData pricingData) throws JBillingException {
		return pricingDataDao.savePricingData(PricingDataConverter.convertPricingDataToEntity(pricingData));
	}

	@Override
	public List<ProductPricingDetails> getPricingProductDetails() {
		return pricingDataDao.getPricingProductDetails();
	}

	@Override
	public List<ProductPricingDetails> getPricingProductDetails(String productId) {
		return pricingDataDao.getPricingProductDetails(productId);
	}
}
