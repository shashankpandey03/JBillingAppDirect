package com.jbilling.appdirect.constants;

public interface JBillingConstants {

	// Constants
	String SUCCESS = "Success";
	String FAILURE = "Failure";

	// Product Queries
	String INSERT_PRODUCT = "INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRICE, DESCRIPTION, CREATE_TIMESTAMP) VALUES (?,?,?,?,?)";
	String UPDATE_PRODUCT = "UPDATE PRODUCT SET PRODUCT_NAME = ? , PRICE = ? , DESCRIPTION = ? ,UPDATE_TIMESTAMP = ? WHERE PRODUCT_ID = ?";
	String DELETE_PRODUCT = "DELETE FROM PRODUCT WHERE PRODUCT_ID = ?";
	String LIST_PRODUCT = "SELECT * FROM PRODUCT";
	String GET_PRODUCT = "SELECT * FROM PRODUCT WHERE PRODUCT_ID = ?";

	// Store queries
	String INSERT_STORE = "INSERT INTO STORE(STORE_ID, STORE_NAME, DESCRIPTION, CREATE_TIMESTAMP) VALUES (?,?,?,?)";
	String UPDATE_STORE = "UPDATE STORE SET STORE_NAME = ? , DESCRIPTION = ?, UPDATE_TIMESTAMP = ? WHERE STORE_ID = ?";
	String DELETE_STORE = "DELETE FROM STORE WHERE STORE_ID = ?";
	String LIST_STORE = "SELECT * FROM STORE";
	String GET_STORE = "SELECT * FROM STORE WHERE STORE_ID = ?";

	// Pricing_Data queries
	String INSERT_PRICING_DATA = "INSERT INTO PRICING(PRICING_ID,STORE_ID, PRODUCT_ID, PRICE, NOTES, CREATE_TIMESTAMP) VALUES (?,?,?,?,?,?)";
	String DELETE_PRICING_DATA_BY_PRODUCTID = "DELETE FROM PRICING WHERE PRODUCT_ID = ?";
	String DELETE_PRICING_DATA_BY_STOREID = "DELETE FROM PRICING WHERE STORE_ID = ?";
	String GET_PRICING_DATA_BY_PRODUCTID = "SELECT * FROM PRICING WHERE PRODUCT_ID = ?";

	// Rules
	String LIST_RULE = "SELECT * FROM RULES";

	/*
	 * String GET_PRODUCT_DETAILS =
	 * "select p.product_id,p.product_name,p.price as base_price,pr.store_id, pr.price from product p "
	 * + "inner join pricing pr on p.product_id=pr.product_id";
	 */

	String GET_PRODUCT_DETAILS = "select p.product_id,p.product_name,p.price as base_price, p.description,pr.max_price,pr.avg_price,pr.min_price, pr.noOfPrices from product p "
			+ "inner join "
			+ "(select product_id, count(product_id) as noOfPrices,max(price) as max_price, avg(price) as avg_price, min(price) as min_price from pricing group by product_id) as pr on p.product_id=pr.product_id";

	// CONSTANTS
	String JSON_RULE = "JSON_RULE";
}
