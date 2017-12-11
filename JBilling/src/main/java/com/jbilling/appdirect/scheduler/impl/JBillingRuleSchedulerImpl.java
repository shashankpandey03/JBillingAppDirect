package com.jbilling.appdirect.scheduler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jbilling.appdirect.cache.JBillingCache;
import com.jbilling.appdirect.constants.JBillingConstants;
import com.jbilling.appdirect.domain.entity.Rules;
import com.jbilling.appdirect.scheduler.JBillingRuleScheduler;
import com.jbilling.appdirect.service.RuleService;

@Component
public class JBillingRuleSchedulerImpl implements JBillingRuleScheduler {

	@Autowired
	private RuleService ruleService;
	
	@Autowired
	private JBillingCache cache;
	
	@Override
	@Scheduled(fixedDelayString = "${rule.scheduler.fixedDelay}")
	public void fetchRule() {
		List<Rules> rules = ruleService.fetchRules();
		if(rules != null && !rules.isEmpty()) {
			cache.put(JBillingConstants.JSON_RULE, rules.get(0).getRule());
		}
	}
}
