package com.jbilling.appdirect.processor.factory;

import java.util.Map;

import com.jbilling.appdirect.processor.IRuleProcessor;

public class RuleProcessorFactory {
	
	private Map<String,IRuleProcessor> ruleProcessorMap;
	
	public IRuleProcessor getRuleProcessor(String ruleProcessor) {
		return ruleProcessorMap.get(ruleProcessor);
	}

	public void setRuleProcessorMap(Map<String, IRuleProcessor> ruleProcessorMap) {
		this.ruleProcessorMap = ruleProcessorMap;
	}
}
