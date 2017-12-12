package com.jbilling.appdirect.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jbilling.appdirect.constants.JBillingConstants;
import com.jbilling.appdirect.dao.RuleDao;
import com.jbilling.appdirect.domain.entity.Rules;

/**
 * 
 * @author ShashankPandey
 * Dao class for fetching rules from rule table
 *
 */
@Component
public class RuleDaoImpl implements RuleDao {

	@Autowired
	private JdbcTemplate template;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Rules> fetchRules() {
		List<Rules> listProduct = template.query(JBillingConstants.LIST_RULE, new RowMapper<Rules>() {
			@Override
			public Rules mapRow(ResultSet rs, int rowNum) throws SQLException {
				Rules p = new Rules();
				p.setRuleId(rs.getString("rule_id"));
				p.setRule(rs.getString("rule"));
				return p;
			}
		});
		return listProduct;
	}
}
