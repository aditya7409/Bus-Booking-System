package com.cognizant.busbooking.service;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

public interface BusService {
	
	ResponseEntity<?> findBus(String source,String destination,LocalDate date);
	
	ResponseEntity<?> bookTicket(int busId,int seatsToBeBookd,ArrayList<String> passengerNames,String source,String destination,String userId);
	
	ResponseEntity<?> viewSchedule(int busId);
	
	ResponseEntity<?> addSeats(int count,int busId);
}
