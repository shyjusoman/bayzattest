package com.mycom.platform.exceptions.response;

import java.util.Date;

public class ApiErrorDetails {
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private Date timestamp;
	private String message;

	public ApiErrorDetails(Date timestamp, String message, String details) {
	    super();
	    this.timestamp = timestamp;
	    this.message = message;
	  }
}
