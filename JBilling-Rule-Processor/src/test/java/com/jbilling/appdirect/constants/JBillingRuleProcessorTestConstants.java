package com.jbilling.appdirect.constants;

public interface JBillingRuleProcessorTestConstants {

	String PRODUCT_ID = "PRODUCT_ID";
	String PRODUCT_DESCRIPTION = "PRODUCT_DESCRIPTION";
	String PRODUCT_NAME = "PRODUCT_NAME";
	
	String STORE_ID = "STORE_ID";
	String STORE_DESCRIPTION = "STORE_DESCRIPTION";
	String STORE_NAME = "STORE_NAME";
	
	String PRICING_ID = "PRICING_ID";
	String PRICING_DESCRIPTION = "PRICING_DESCRIPTION";
	
	String VALID_JSON_RULE = "{\"LIST_MODIFIER\":\"0,-0\",\"AVERAGE_CALCULATOR\":\"\",\"PERCENTAGE_CALCULATOR\":\"20\"}";
	String VALID_JSON_RULE_1 = "{\"LIST_MODIFIER\":\"1\",\"AVERAGE_CALCULATOR\":\"\",\"PERCENTAGE_CALCULATOR\":\"20\"}";
	String VALID_JSON_RULE_2 = "{\"LIST_MODIFIER\":\"-1\",\"AVERAGE_CALCULATOR\":\"\",\"PERCENTAGE_CALCULATOR\":\"20\"}";
	String VALID_JSON_RULE_3 = "{\"LIST_MODIFIER\":\"-1\",\"AVERAGE_CALCULATOR\":\"\",\"PERCENTAGE_CALCULATOR\":\"-20\"}";
	
	String INVALID_JSON_RULE = "{\"DUMMY_INVALID_RULE\":\"0,-0\",\"AVERAGE_CALCULATOR\":\"\",\"PERCENTAGE_CALCULATOR\":\"20\"}";
	String START_COMMAND = "start";
	String END_COMMAND = "end";
}
