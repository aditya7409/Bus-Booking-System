package com.cognizant.ticket.data;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="bus-ms")
public interface BusServiceClient{
	
	@PutMapping("users/addSeats")
	ResponseEntity<?> addSeats(@RequestParam("count") int count,@RequestParam("busId") int busId);
	
}