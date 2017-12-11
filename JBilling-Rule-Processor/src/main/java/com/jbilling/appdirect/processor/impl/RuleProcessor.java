package com.jbilling.appdirect.processor.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jbilling.appdirect.domain.response.RequestObject;
import com.jbilling.appdirect.processor.IRuleProcessor;

public class RuleProcessor extends IRuleProcessor {

	private Logger logger = LoggerFactory.getLogger(RuleProcessor.class);
	
	private IRuleProcessor ruleProcessor;
	private String input;
	
	@Override
	public Object processRule(RequestObject requestObject) {
		logger.debug("Input : " + input);
		logger.debug("Processing basic rule");
		
		logger.debug("Sorting list of prices in ascending order");
		Collections.sort((List<Integer>)requestObject.getRequest());
		
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

	@Override
	public void setRuleInput(String input) {
		this.input = input;
	}

}
