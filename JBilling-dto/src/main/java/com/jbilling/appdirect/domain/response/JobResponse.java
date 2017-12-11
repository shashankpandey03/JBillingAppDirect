package com.jbilling.appdirect.domain.response;

import java.util.Date;

public class JobResponse {

	private String jobName;
	private Date startedDate;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Date getStartedDate() {
		return startedDate;
	}

	public void setStartedDate(Date startedDate) {
		this.startedDate = startedDate;
	}
}
