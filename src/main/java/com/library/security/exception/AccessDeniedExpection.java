package com.library.security.exception;

@SuppressWarnings("serial")
public class AccessDeniedExpection extends RuntimeException {

	private String message;

	public AccessDeniedExpection(String message) {
		super(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
