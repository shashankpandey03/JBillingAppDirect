package com.jbilling.appdirect.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbilling.appdirect.domain.response.ProductPricingDetails;
import com.jbilling.appdirect.domain.response.RequestObject;
import com.jbilling.appdirect.processor.IRuleProcessor;
import com.jbilling.appdirect.processor.factory.RuleProcessorFactory;
import com.jbilling.appdirect.processor.impl.RuleProcessor;
import com.jbilling.appdirect.service.IRuleProcessorService;

/**
 * Service class to create chain of decorators based on rules defines
 * in json. It picks key from json , looks it up in factory and brings 
 * corresponding decorator and creates a chain passing the value from json as input
 * @author ShashankPandey
 *
 */
@Component
public class RuleProcessorServiceImpl implements IRuleProcessorService {

	private Logger logger = LoggerFactory.getLogger(RuleProcessorServiceImpl.class);
	
	@Autowired
	private RuleProcessorFactory factory;
	
	@Override
	public void processRules(String jsonRule, Map<ProductPricingDetails,List<Integer>> map) throws Exception {
		logger.debug("Printing rules : " + jsonRule);
		
		IRuleProcessor ruleProcessor = null;
		IRuleProcessor processor = null;
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			LinkedHashMap<String, String> ruleMap = (LinkedHashMap)mapper.readValue(jsonRule, LinkedHashMap.class);
			
			if(ruleMap != null && ruleMap.size() != 0) {
				ListIterator<Map.Entry<String, String>> iterator = new ArrayList<Map.Entry<String, String>>(ruleMap.entrySet()).listIterator(ruleMap.size());
				
				Map.Entry<String, String> entry = null;
				
				logger.debug("Creating chain of rules to be executed by fetching rule decorators from factory");

				while (iterator.hasPrevious()) {
					entry = iterator.previous();
					
					logger.debug("Fetching decorator for key : " + entry.getKey());
					processor = factory.getRuleProcessor(entry.getKey());
					if(processor != null) {
						processor.setRuleProcessor(ruleProcessor);
						processor.setRuleInput(entry.getValue());
						ruleProcessor = processor;
					} else {
						logger.error("Invalid rule set provided. No rule configured for " + entry.getKey());
						throw new Exception("Invalid rule set provided. No rule configured for " + entry.getKey());
					}
				}
				processor = new RuleProcessor();
				processor.setRuleInput(jsonRule);
				processor.setRuleProcessor(ruleProcessor);
				ruleProcessor = processor;
			}
			
			logger.debug("Executing rule chain for every product id");
			if(map != null && !map.isEmpty()) {
				
				RequestObject requestObject = new RequestObject();
				
				for(Map.Entry<ProductPricingDetails,List<Integer>> entry : map.entrySet()) {
					requestObject.setRequest(entry.getValue());
					ruleProcessor.processRule(requestObject);
					entry.getKey().setIdealPrice(requestObject.getIntermittentResult());
				}
			}
		} catch (Exception e) {
			logger.error("Invalid rule json provided");
			throw new Exception("Invalid rule json provided");
		}
	}
}
