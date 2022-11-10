package com.cognizant.Audit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.Audit.entity.Audit;
import com.cognizant.Audit.repository.AuditRepository;

@Service
public class AuditServiceImpl implements AuditService {

	@Autowired
	AuditRepository auditRepository;
	
	@Override
	public void addDetails(String serviceName, String ip, long reqTime, long resTime, long processingTime, String user,
			int responseCode) {
		this.auditRepository.save(new Audit(serviceName,ip,reqTime,reqTime,processingTime,user,responseCode));
	}

}
