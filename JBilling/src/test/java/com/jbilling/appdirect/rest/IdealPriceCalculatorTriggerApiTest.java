package com.jbilling.appdirect.rest;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jbilling.appdirect.cache.JBillingCache;
import com.jbilling.appdirect.constants.JBillingConstants;
import com.jbilling.appdirect.constants.JBillingTestConstants;
import com.jbilling.appdirect.dao.PricingDataDao;
import com.jbilling.appdirect.util.JBillingTestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(value = IdealPriceCalculatorTriggerApi.class, secure = false)
public class IdealPriceCalculatorTriggerApiTest {

	@MockBean
	private JBillingCache cache;
	
	@MockBean
	private PricingDataDao pricingDataDao;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		Mockito.when(cache.get(JBillingConstants.JSON_RULE)).thenReturn(JBillingTestConstants.VALID_JSON_RULE);
		Mockito.when(pricingDataDao.getPricingProductDetails()).thenReturn(JBillingTestUtil.getProductPricingDetailsList());
		Mockito.when(pricingDataDao.getPricingProductDetails(JBillingTestConstants.PRODUCT_ID)).thenReturn(JBillingTestUtil.getProductPricingDetailsList());
	}

	@Test
	public void testTriggerIdealPriceCalculator() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/jobs/pricecalculator?command=" + JBillingTestConstants.START_COMMAND)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("202"));
	}
	
	@Test
	public void testTriggerIdealPriceCalculatorWithInvalidCommand() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/jobs/pricecalculator?command=" + JBillingTestConstants.END_COMMAND)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("400"));
	}

}
