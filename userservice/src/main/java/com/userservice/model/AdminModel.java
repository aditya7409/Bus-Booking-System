package com.userservice.model;



public class AdminModel {

	private String adminClaimDescription;
	private String claimStatus;
	public String getAdminClaimDescription() {
		return adminClaimDescription;
	}
	public void setAdminClaimDescription(String adminClaimDescription) {
		this.adminClaimDescription = adminClaimDescription;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public AdminModel(String adminClaimDescription, String claimStatus) {
		super();
		this.adminClaimDescription = adminClaimDescription;
		this.claimStatus = claimStatus;
	}
	
	
}
