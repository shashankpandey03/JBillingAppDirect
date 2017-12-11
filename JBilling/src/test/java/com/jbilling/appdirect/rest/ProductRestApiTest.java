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
import com.jbilling.appdirect.dao.ProductDao;
import com.jbilling.appdirect.domain.entity.ProductEntity;
import com.jbilling.appdirect.exception.JBillingException;
import com.jbilling.appdirect.util.JBillingTestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductRestApi.class, secure = false)
public class ProductRestApiTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductDao productDao;

	@Before
	public void setUp() throws JBillingException {
		Mockito.when(productDao.list()).thenReturn(JBillingTestUtil.getProductList());
		Mockito.when(productDao.getProduct(JBillingTestConstants.PRODUCT_ID)).thenReturn(JBillingTestUtil.getProductEntity());
	}

	@Test
	public void testGetAllProducts() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertNotNull(result.getResponse().getContentAsString());
	}

	@Test
	public void testGetProduct() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/" + JBillingTestConstants.PRODUCT_ID)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertNotNull(result.getResponse().getContentAsString());
	}

	@Test
	public void testGetProductWhenNotExist() throws Exception {
		Mockito.when(productDao.getProduct(JBillingTestConstants.PRODUCT_ID)).thenReturn(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/" + JBillingTestConstants.PRODUCT_ID)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("404"));
	}

	@Test
	public void testDeleteProduct() throws Exception {
		Mockito.when(productDao.delete(JBillingTestConstants.PRODUCT_ID)).thenReturn(1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/product/" + JBillingTestConstants.PRODUCT_ID)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertNotNull(result.getResponse().getContentAsString());
	}

	@Test
	public void testDeleteProductWhenNotExist() throws Exception {
		Mockito.when(productDao.delete(JBillingTestConstants.PRODUCT_ID)).thenReturn(0);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/product/" + JBillingTestConstants.PRODUCT_ID)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("404"));
	}

	@Test
	public void testCreateProduct() throws Exception {
		Mockito.when(productDao.saveProduct(Mockito.any(ProductEntity.class))).thenReturn(JBillingTestUtil.getProductEntity());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/product").accept(MediaType.APPLICATION_JSON)
				.content(JBillingTestUtil.getProductJson(JBillingTestUtil.getProduct())).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertNotNull(result.getResponse().getContentAsString());
		assertTrue(result.getResponse().getContentAsString().contains("200"));
	}
	
	@Test
	public void testUpdateProduct() throws Exception {
		Mockito.when(productDao.updateProduct(Mockito.any(ProductEntity.class))).thenReturn(JBillingTestUtil.getProductEntity());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/product").accept(MediaType.APPLICATION_JSON)
				.content(JBillingTestUtil.getProductJson(JBillingTestUtil.getProduct())).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertNotNull(result.getResponse().getContentAsString());
		assertTrue(result.getResponse().getContentAsString().contains("200"));
	}
	
	@Test
	public void testUpdateProductWhenNotExist() throws Exception {
		Mockito.when(productDao.updateProduct(Mockito.any(ProductEntity.class))).thenThrow(new JBillingException());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/product").accept(MediaType.APPLICATION_JSON)
				.content(JBillingTestUtil.getProductJson(JBillingTestUtil.getProduct())).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertNotNull(result.getResponse().getContentAsString());
		assertTrue(result.getResponse().getContentAsString().contains("400"));
	}
}
