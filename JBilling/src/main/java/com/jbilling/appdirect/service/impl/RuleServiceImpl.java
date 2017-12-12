package com.jbilling.appdirect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbilling.appdirect.dao.RuleDao;
import com.jbilling.appdirect.domain.entity.Rules;
import com.jbilling.appdirect.service.RuleService;

/**
 * Service class for fetching json rules
 * @author ShashankPandey
 *
 */
@Component
public class RuleServiceImpl implements RuleService {

	@Autowired
	private RuleDao ruleDao;
	
	@Override
	public List<Rules> fetchRules() {
		return ruleDao.fetchRules();
	}
}
