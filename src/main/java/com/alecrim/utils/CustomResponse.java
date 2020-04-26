package com.alecrim.utils;

import org.springframework.http.HttpStatus;

public class CustomResponse {

	private HttpStatus status;
	private String message;

	public CustomResponse(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setSatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return status + " - " + message;
	}
}
