package com.jbilling.appdirect.processor.factory;

import java.util.Map;

import com.jbilling.appdirect.processor.IRuleProcessor;

/**
 * Factory class to return decorator corresponding to rule json key.
 * It contains a map of rule and its corresponding decorator to be applied
 * @author ShashankPandey
 *
 */
public class RuleProcessorFactory {
	
	private Map<String,IRuleProcessor> ruleProcessorMap;
	
	public IRuleProcessor getRuleProcessor(String ruleProcessor) {
		return ruleProcessorMap.get(ruleProcessor);
	}

	public void setRuleProcessorMap(Map<String, IRuleProcessor> ruleProcessorMap) {
		this.ruleProcessorMap = ruleProcessorMap;
	}
}
