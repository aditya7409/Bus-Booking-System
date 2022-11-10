package com.userservice.data;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="audit-ms")
public interface AuditServiceClient {

	@PostMapping("users/audit")
	void addDetails(@RequestParam("serviceName") String serviceName,@RequestParam("ip") String ip,@RequestParam("reqTime") long reqTime,@RequestParam("resTime") long resTime,@RequestParam("processingTime") long processingTime,@RequestParam("user") String user,@RequestParam("responseCode") int responseCode);
}
