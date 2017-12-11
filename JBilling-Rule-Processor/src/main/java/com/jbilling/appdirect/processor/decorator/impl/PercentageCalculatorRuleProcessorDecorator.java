package com.jbilling.appdirect.processor.decorator.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jbilling.appdirect.domain.response.RequestObject;
import com.jbilling.appdirect.processor.IRuleProcessor;
import com.jbilling.appdirect.processor.decorator.IRuleProcessorDecorator;

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
				
			} catch(Exception e) {
				e.printStackTrace();
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
