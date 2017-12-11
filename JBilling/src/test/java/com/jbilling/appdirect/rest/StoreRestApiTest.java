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

import com.jbilling.appdirect.constants.JBillingTestConstants;
import com.jbilling.appdirect.dao.StoreDao;
import com.jbilling.appdirect.domain.entity.StoreEntity;
import com.jbilling.appdirect.exception.JBillingException;
import com.jbilling.appdirect.exception.JBillingResourceNotFoundException;
import com.jbilling.appdirect.util.JBillingTestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StoreRestApi.class, secure = false)
public class StoreRestApiTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StoreDao storeDao;
	
	@Before
	public void setUp() throws JBillingException {
		Mockito.when(storeDao.list()).thenReturn(JBillingTestUtil.getStoreList());
		Mockito.when(storeDao.getStore(JBillingTestConstants.STORE_ID)).thenReturn(JBillingTestUtil.getStoreEntity());
	}

	@Test
	public void testGetAllStores() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/store").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertNotNull(result.getResponse().getContentAsString());
	}

	@Test
	public void testGetStore() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/store/" + JBillingTestConstants.STORE_ID)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertNotNull(result.getResponse().getContentAsString());
	}

	@Test
	public void testGetStoreWhenNotExist() throws Exception {
		Mockito.when(storeDao.getStore(JBillingTestConstants.STORE_ID)).thenReturn(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/store/" + JBillingTestConstants.STORE_ID)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("404"));
	}

	@Test
	public void testDeleteStore() throws Exception {
		Mockito.when(storeDao.delete(JBillingTestConstants.STORE_ID)).thenReturn(1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/store/" + JBillingTestConstants.STORE_ID)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertNotNull(result.getResponse().getContentAsString());
	}

	@Test
	public void testDeleteStoreWhenNotExist() throws Exception {
		Mockito.when(storeDao.delete(JBillingTestConstants.STORE_ID)).thenReturn(0);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/store/" + JBillingTestConstants.STORE_ID)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("404"));
	}

	@Test
	public void testCreateStore() throws Exception {
		Mockito.when(storeDao.saveStore(Mockito.any(StoreEntity.class))).thenReturn(JBillingTestUtil.getStoreEntity());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/store").accept(MediaType.APPLICATION_JSON)
				.content(JBillingTestUtil.getStoreJson(JBillingTestUtil.getStore())).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertNotNull(result.getResponse().getContentAsString());
		assertTrue(result.getResponse().getContentAsString().contains("200"));
	}
	
	@Test
	public void testUpdateStore() throws Exception {
		Mockito.when(storeDao.updateStore(Mockito.any(StoreEntity.class))).thenReturn(JBillingTestUtil.getStoreEntity());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/store").accept(MediaType.APPLICATION_JSON)
				.content(JBillingTestUtil.getStoreJson(JBillingTestUtil.getStore())).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertNotNull(result.getResponse().getContentAsString());
		assertTrue(result.getResponse().getContentAsString().contains("200"));
	}
	
	@Test
	public void testUpdateStoreWhenNotExist() throws Exception {
		Mockito.when(storeDao.updateStore(Mockito.any(StoreEntity.class))).thenThrow(new JBillingResourceNotFoundException());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/store").accept(MediaType.APPLICATION_JSON)
				.content(JBillingTestUtil.getStoreJson(JBillingTestUtil.getStore())).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertNotNull(result.getResponse().getContentAsString());
		assertTrue(result.getResponse().getContentAsString().contains("400"));
	}
}
