package com.cognizant.booking.data;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="ticket-ms")
public interface TicketServiceClient {

	@GetMapping("users/findTicketDetails")
	public ResponseEntity<?> getTicketDetails(@RequestParam("bookingId") String bookingId);
	
	@PutMapping("users/cancelBooking/{bookingId}")
	public ResponseEntity<?> cancelBooking(@RequestParam("bookingId") String bookingId);
}
