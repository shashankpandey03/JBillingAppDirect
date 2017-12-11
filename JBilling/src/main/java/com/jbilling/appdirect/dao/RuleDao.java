package com.jbilling.appdirect.dao;

import java.util.List;

import com.jbilling.appdirect.domain.entity.Rules;

public interface RuleDao {
	
	public List<Rules> fetchRules();

}
