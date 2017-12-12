package com.jbilling.appdirect.processor.decorator.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jbilling.appdirect.domain.response.RequestObject;
import com.jbilling.appdirect.processor.IRuleProcessor;
import com.jbilling.appdirect.processor.decorator.IRuleProcessorDecorator;

/**
 * Class to calculate average of lit of prices provided.
 * It takes List of integer prices and calculates the average.
 * An empty input is provided as json input for this decorator
 * @author ShashankPandey
 *
 */
public class AverageCalculatorRuleProcessorDecorator extends IRuleProcessorDecorator {

	private Logger logger = LoggerFactory.getLogger(AverageCalculatorRuleProcessorDecorator.class);
	
	private IRuleProcessor ruleProcessor;
	private String input;

	@Override
	public void setRuleInput(String input) {
		this.input = input;
	}
	
	@Override
	public Object processRule(RequestObject requestObject) {
		logger.debug("Input : " + input);
		logger.debug("Calculating average");
		
		if(requestObject.getRequest() instanceof List) {
			List<Integer> priceList = (List<Integer>) requestObject.getRequest();
			int sum = 0;
			if(priceList != null && !priceList.isEmpty()) {
				for(Integer value : priceList) {
					sum = sum + value;
				}
				requestObject.setIntermittentResult(sum/priceList.size());
			}
		}
		logger.debug("Average calculation done");
		if(ruleProcessor != null) {
			return ruleProcessor.processRule(requestObject);
		} else {
			return null;
		}
	}

	@Override
	public void setRuleProcessor(IRuleProcessor processor) {
		ruleProcessor = processor;
	}
}
