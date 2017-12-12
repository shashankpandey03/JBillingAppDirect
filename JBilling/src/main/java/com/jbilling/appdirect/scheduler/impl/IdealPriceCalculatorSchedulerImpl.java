package com.jbilling.appdirect.scheduler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jbilling.appdirect.scheduler.IdealPriceCalculatorScheduler;
import com.jbilling.appdirect.service.IdealPriceCalculatorService;

/**
 * 
 * @author ShashankPandey
 * Scheduler to continuously calculate ideal price for all the productIds
 * available and store them in cache. Time interval is configurable
 *
 */
@Component
public class IdealPriceCalculatorSchedulerImpl implements IdealPriceCalculatorScheduler {

	@Autowired
	private IdealPriceCalculatorService iDealPriceCalculatorService;
	
	@Override
	@Scheduled(fixedDelayString = "${idealprice.scheduler.fixedDelay}")
	public void processPriceCalculatorJob() {
		try {
			iDealPriceCalculatorService.calculateIdealPrice();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
