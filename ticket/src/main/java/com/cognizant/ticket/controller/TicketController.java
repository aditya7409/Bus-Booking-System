package com.cognizant.ticket.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.ticket.service.TicketService;

@RestController
@RequestMapping("/users")
public class TicketController { 
	
	@Autowired
	private TicketService ticketService;
	
	@PostMapping("/addPassenger")
	public void addPasenger(@RequestParam("passengerNames") ArrayList<String> passengerNames,@RequestParam("seatNumbers") ArrayList<Integer> seatNumbers,@RequestParam("source") String source,@RequestParam("destination") String destination,@RequestParam("fare") int fare,@RequestParam("bookingId") String bookingId,@RequestParam("busId") int busId){
		this.ticketService.addPassenger(passengerNames,seatNumbers,source,destination,fare,bookingId,busId);
	}
	
	@GetMapping("/findTicketDetails")
	public ResponseEntity<?> getTicketDetails(@RequestParam("bookingId") String bookingId) {
		return this.ticketService.findTicket(bookingId);
	}
	
	@PutMapping("/cancelBooking/{bookingId}")
	public ResponseEntity<?> cancelBooking(@RequestParam("bookingId") String bookingId){
		return this.ticketService.cancelTickets(bookingId);
	}
	
}


