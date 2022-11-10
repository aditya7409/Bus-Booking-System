package com.userservice.data;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.userservice.model.Booking;

@FeignClient(name="booking-ms")
public interface BookingServiceClient {

	@PostMapping("users/addBooking")
	public void addBooking(Booking booking);
	
	@GetMapping("users/upcomingBookings/{userId}")
	public ResponseEntity<?> upcomingBookings(@RequestParam("userId") String userId);
	
	@GetMapping("users/pastBookings/{userId}")
	public ResponseEntity<?> pastBookings(@RequestParam("userId") String userId);
	
	@PutMapping("users/cancelBooking/{bookingId}")
	public ResponseEntity<?> cancelBooking(@RequestParam("bookingId") String bookingId);
	
	
}
