package com.cognizant.ticket.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface TicketService {

	void addPassenger(List<String> passengerNames,List<Integer> seatNo,String source,String destination,int fare,String bookingId,int busId);
	
	ResponseEntity<?> findTicket(String bookingId);
	
	ResponseEntity<?> cancelTickets(String bookingId);
}
