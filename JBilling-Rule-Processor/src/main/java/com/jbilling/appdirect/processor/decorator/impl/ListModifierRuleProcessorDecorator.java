package com.jbilling.appdirect.processor.decorator.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jbilling.appdirect.domain.response.RequestObject;
import com.jbilling.appdirect.processor.IRuleProcessor;
import com.jbilling.appdirect.processor.decorator.IRuleProcessorDecorator;

public class ListModifierRuleProcessorDecorator extends IRuleProcessorDecorator {

	private Logger logger = LoggerFactory.getLogger(ListModifierRuleProcessorDecorator.class);
	
	private IRuleProcessor ruleProcessor;
	private String input;

	@Override
	public void setRuleInput(String input) {
		this.input = input;
	}

	@Override
	public Object processRule(RequestObject requestObject) {
		logger.debug("Input for rule : " + input);
		logger.debug("Modifying list");

		if (input != null && !input.isEmpty()) {
			String arr[] = input.split(",");
			if (arr.length >= 1 && arr.length <= 2) {
				for (String s : arr) {
					List<Integer> list = requestObject.getRequest();

					int index = Integer.parseInt(s);
					if(list.size() >= Math.abs(index)) {
						if (index > 0) {
							list = list.subList(index, list.size());
						} else if (index < 0) {
							list = list.subList(0, list.size() + index);
						}
						requestObject.setRequest(list);
					}
				}
			}
		}

		if (ruleProcessor != null) {
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
