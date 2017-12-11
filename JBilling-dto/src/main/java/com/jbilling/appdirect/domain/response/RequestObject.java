package com.jbilling.appdirect.domain.response;

import java.util.List;

public class RequestObject {

	private List<Integer> request;
	private Integer intermittentResult;

	public List<Integer> getRequest() {
		return request;
	}

	public void setRequest(List<Integer> request) {
		this.request = request;
	}

	public Integer getIntermittentResult() {
		return intermittentResult;
	}

	public void setIntermittentResult(Integer intermittentResult) {
		this.intermittentResult = intermittentResult;
	}
}
