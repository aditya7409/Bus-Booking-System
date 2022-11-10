package com.userservice.model;

public class UnAuthorizedAccess {
	
	private String message;

	public UnAuthorizedAccess(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
