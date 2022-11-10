package com.cognizant.booking.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.booking.entity.Booking;
import com.cognizant.booking.service.BookingService;

@RestController
@RequestMapping("/users")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/addBooking")
	public void addBooking(@RequestParam("bookingId") String bookingId,@RequestParam("userId") String userId,@RequestParam("seatsToBeBooked") int seatsToBeBooked,@RequestParam("date") LocalDate date,@RequestParam("status") String status) {
		System.out.println("Inside Booking controller");
		Booking booking=new Booking(bookingId,userId,seatsToBeBooked,date,status);
		this.bookingService.addBooking(booking);
	}
	
	@GetMapping("/upcomingBookings/{userId}")
	public ResponseEntity<?> upcomingBookings(@RequestParam("userId") String userId) {
		return this.bookingService.findUpcomingBookings(userId);
	}
	
	@GetMapping("/pastBookings/{userId}")
	public ResponseEntity<?> pastBookings(@RequestParam("userId")  String userId) {
		return this.bookingService.findPastBookings(userId);
	}
	@PutMapping("/cancelBooking/{bookingId}")
	public ResponseEntity<?> cancelBooking(@RequestParam("bookingId") String bookingId){
		return this.bookingService.cancelBooking(bookingId);
	}
}
