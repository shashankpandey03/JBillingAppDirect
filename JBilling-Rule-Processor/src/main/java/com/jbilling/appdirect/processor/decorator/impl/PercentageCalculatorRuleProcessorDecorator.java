package com.jbilling.appdirect.processor.decorator.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jbilling.appdirect.domain.response.RequestObject;
import com.jbilling.appdirect.processor.IRuleProcessor;
import com.jbilling.appdirect.processor.decorator.IRuleProcessorDecorator;

/**
 * Class to calculate percentage and depending upon positive or negative ,
 * it adds or deducts
 * @author ShashankPandey
 *
 */
public class PercentageCalculatorRuleProcessorDecorator extends IRuleProcessorDecorator {

	private Logger logger = LoggerFactory.getLogger(PercentageCalculatorRuleProcessorDecorator.class);
	
	private IRuleProcessor ruleProcessor;
	private String input;

	@Override
	public void setRuleInput(String input) {
		this.input = input;
	}

	@Override
	public Object processRule(RequestObject requestObject) {
		logger.debug("Input : " + input);
		logger.debug("Adding percentage");
		
		if(input != null && !input.isEmpty()) {
			
			try {
				int percentage = Integer.parseInt(input);
				boolean isPositive = false;
				
				logger.debug("Percentage to be calculated : " + percentage);
				
				if(percentage > 0) {
					isPositive = true;
				}
				
				percentage = Math.abs(percentage);
				int postPercentageCalculation = 0;
				
				if(isPositive) {
					postPercentageCalculation = requestObject.getIntermittentResult() + (requestObject.getIntermittentResult() * percentage)/100;
				} else {
					postPercentageCalculation = requestObject.getIntermittentResult() - (requestObject.getIntermittentResult() * percentage)/100;
				}
				requestObject.setIntermittentResult(postPercentageCalculation);
				logger.debug("Percentage calculation done.");
			} catch(Exception e) {
				logger.error("Error occurred while percentage calculation. " + e.getMessage());
			}
		}
		
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
