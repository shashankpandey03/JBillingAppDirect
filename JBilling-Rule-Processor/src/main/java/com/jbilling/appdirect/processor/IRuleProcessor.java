package com.jbilling.appdirect.processor;

import com.jbilling.appdirect.domain.response.RequestObject;

public abstract class IRuleProcessor {

	public abstract Object processRule(RequestObject requestObject);
	public abstract void setRuleProcessor(IRuleProcessor ruleProcessor);
	public abstract void setRuleInput(String input);
}
