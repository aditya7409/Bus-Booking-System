package com.userservice.model;

public class GeneralResponseModel {
	private String message;

	public GeneralResponseModel(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
