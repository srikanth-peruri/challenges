package com.speruri.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControllerException {

	private String status;
	private String message;

	public ControllerException(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
