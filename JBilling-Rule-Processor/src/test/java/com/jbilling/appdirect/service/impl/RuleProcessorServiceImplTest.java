package com.jbilling.appdirect.service.impl;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jbilling.appdirect.AppConfig;
import com.jbilling.appdirect.constants.JBillingRuleProcessorTestConstants;
import com.jbilling.appdirect.domain.response.ProductPricingDetails;
import com.jbilling.appdirect.service.IRuleProcessorService;
import com.jbilling.appdirect.util.JBillingRuleProcessorUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class RuleProcessorServiceImplTest {

	@Autowired
	private IRuleProcessorService ruleProcessorService;
	
	@Test
	public void testProcessRules() throws Exception {
		
		ProductPricingDetails details = JBillingRuleProcessorUtil.getProductPricingDetails();
		Map<ProductPricingDetails,List<Integer>> map = new  HashMap<ProductPricingDetails,List<Integer>>();
		map.put(details, JBillingRuleProcessorUtil.getPriceList());
		
		ruleProcessorService.processRules(JBillingRuleProcessorTestConstants.VALID_JSON_RULE, map);
		assertTrue(details.getIdealPrice() == 180);
	}
	
	@Test
	public void testProcessRulesWithIndexPositiveIndex() throws Exception {
		
		ProductPricingDetails details = JBillingRuleProcessorUtil.getProductPricingDetails();
		Map<ProductPricingDetails,List<Integer>> map = new  HashMap<ProductPricingDetails,List<Integer>>();
		map.put(details, JBillingRuleProcessorUtil.getPriceList());
		
		ruleProcessorService.processRules(JBillingRuleProcessorTestConstants.VALID_JSON_RULE_1, map);
		assertTrue(details.getIdealPrice() == 240);
	}
	
	@Test
	public void testProcessRulesWithNegativeIndex() throws Exception {
		
		ProductPricingDetails details = JBillingRuleProcessorUtil.getProductPricingDetails();
		Map<ProductPricingDetails,List<Integer>> map = new  HashMap<ProductPricingDetails,List<Integer>>();
		map.put(details, JBillingRuleProcessorUtil.getPriceList());
		
		ruleProcessorService.processRules(JBillingRuleProcessorTestConstants.VALID_JSON_RULE_2, map);
		assertTrue(details.getIdealPrice() == 120);
	}
	
	@Test
	public void testProcessRulesWithNegativePercentage() throws Exception {
		ProductPricingDetails details = JBillingRuleProcessorUtil.getProductPricingDetails();
		Map<ProductPricingDetails,List<Integer>> map = new  HashMap<ProductPricingDetails,List<Integer>>();
		map.put(details, JBillingRuleProcessorUtil.getPriceList());
		
		ruleProcessorService.processRules(JBillingRuleProcessorTestConstants.VALID_JSON_RULE_3, map);
		assertTrue(details.getIdealPrice() == 80);
	}
	
	@Test(expected = Exception.class)
	public void testProcessRulesWithInvalidRule() throws Exception {
		
		ProductPricingDetails details = JBillingRuleProcessorUtil.getProductPricingDetails();
		Map<ProductPricingDetails,List<Integer>> map = new  HashMap<ProductPricingDetails,List<Integer>>();
		map.put(details, JBillingRuleProcessorUtil.getPriceList());
		
		ruleProcessorService.processRules(JBillingRuleProcessorTestConstants.INVALID_JSON_RULE, map);
		assertTrue(details.getIdealPrice() == 180);
	}
	
	@Test(expected = Exception.class)
	public void testProcessRulesWithEmptyRule() throws Exception {
		
		ProductPricingDetails details = JBillingRuleProcessorUtil.getProductPricingDetails();
		Map<ProductPricingDetails,List<Integer>> map = new  HashMap<ProductPricingDetails,List<Integer>>();
		map.put(details, JBillingRuleProcessorUtil.getPriceList());
		
		ruleProcessorService.processRules("", map);
		assertTrue(details.getIdealPrice() == 180);
	}

}
