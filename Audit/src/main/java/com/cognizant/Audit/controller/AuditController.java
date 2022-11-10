package com.cognizant.Audit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.Audit.service.AuditService;

@RestController
@RequestMapping("/users")
public class AuditController {

	@Autowired
	private AuditService auditService;
	
	@PostMapping("/audit")
	void addDetails(@RequestParam("serviceName") String serviceName,@RequestParam("ip") String ip,@RequestParam("reqTime") long reqTime,@RequestParam("resTime") long resTime,@RequestParam("processingTime") long processingTime,@RequestParam("user") String user,@RequestParam("responseCode") int responseCode) {
		this.auditService.addDetails(serviceName, ip, reqTime, resTime, processingTime, user, responseCode);
	}
}
