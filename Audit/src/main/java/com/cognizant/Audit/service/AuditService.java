package com.cognizant.Audit.service;

public interface AuditService {
	void addDetails(String serviceName,String ip,long reqTime,long resTime,long processingTime,String user,int responseCode);
}
