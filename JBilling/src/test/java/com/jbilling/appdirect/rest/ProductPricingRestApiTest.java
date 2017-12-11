package com.jbilling.appdirect.rest;

import static org.junit.Assert.assertNotNull;
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

import com.jbilling.appdirect.dao.PricingDataDao;
import com.jbilling.appdirect.domain.entity.PricingDataEntity;
import com.jbilling.appdirect.util.JBillingTestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductPricingRestApi.class, secure = false)
public class ProductPricingRestApiTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PricingDataDao pricingDataDao;
	
	@Before
	public void setUp() throws Exception {
		Mockito.when(pricingDataDao.savePricingData(Mockito.any(PricingDataEntity.class))).thenReturn(JBillingTestUtil.getPricingDataEntity());
	}

	@Test
	public void testCreateProduct() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/store/product").accept(MediaType.APPLICATION_JSON)
				.content(JBillingTestUtil.getProductJson(JBillingTestUtil.getPricingData())).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertNotNull(result.getResponse().getContentAsString());
		assertTrue(result.getResponse().getContentAsString().contains("200"));
	}
}
