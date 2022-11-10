package com.cognizant.booking.service;

import org.springframework.http.ResponseEntity;

import com.cognizant.booking.entity.Booking;

public interface BookingService {
	
	void addBooking(Booking book);
	
	ResponseEntity<?> findUpcomingBookings(String userId);
	
	ResponseEntity<?> findPastBookings(String userId);
	
	ResponseEntity<?> cancelBooking(String bookingId);
}
