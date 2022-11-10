package com.cognizant.busbooking.data;

import java.time.LocalDate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="booking-ms")
public interface BookingServiceClient {

	@PostMapping("users/addBooking")
	public void addBooking(@RequestParam("bookingId") String bookingId,@RequestParam("userId") String userId,@RequestParam("seatsToBeBooked") int seatsToBeBooked,@RequestParam("date") LocalDate date,@RequestParam("status") String status);
	
	@GetMapping("users/upcomingBookings")
	public ResponseEntity<?> upcomingBookings(String bookingId);
	
	@GetMapping("users/pastBookings")
	public ResponseEntity<?> pastBookings(String bookingId);
	
	@PutMapping("users/cancelBooking")
	public ResponseEntity<?> cancelBooking(String bookingId);
	
	
}
