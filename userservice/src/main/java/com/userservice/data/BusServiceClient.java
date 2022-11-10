package com.userservice.data;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="bus-ms")
public interface BusServiceClient {
	
	@GetMapping("users/findBus")
	ResponseEntity<?> findBus(@RequestParam("source") String source,@RequestParam("destination") String destination,@RequestParam("date") LocalDate date);
	
	@PostMapping("users/bookTicket")
	public ResponseEntity<?> bookTicket(@RequestParam("busId") int busId,@RequestParam("seatsToBeBooked") int seatsToBeBookd,@RequestParam("passengerNames") ArrayList<String> passengerNames,@RequestParam("source") String source,@RequestParam("destination") String destination,@RequestParam("userId") String userId);
	
	@GetMapping("users/viewSchedule/{busId}")
	public ResponseEntity<?> viewSchedule(@RequestParam("busId") int busId);
}
