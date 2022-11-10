package com.cognizant.busbooking.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.busbooking.service.BusService;

@RestController
@RequestMapping("/users")
public class BusController {
	
	@Autowired
	private BusService busService;
	
	@GetMapping("/findBus")
	public ResponseEntity<?> findBus(@RequestParam("source") String source,@RequestParam("destination") String destination,@RequestParam("date") LocalDate date){
		return this.busService.findBus(source,destination,date);
	}
	
	@PostMapping("/bookTicket")
	public ResponseEntity<?> bookTicket(@RequestParam("busId") int busId,@RequestParam("seatsToBeBooked") int seatsToBeBooked,@RequestParam("passengerNames") ArrayList<String> passengerNames,@RequestParam("source") String source,@RequestParam("destination") String destination,@RequestParam("userId") String userId){
			return this.busService.bookTicket(busId,seatsToBeBooked,passengerNames,source,destination,userId);
	}
	@GetMapping("/viewSchedule/{busId}")
	public ResponseEntity<?> viewSchedule(@RequestParam("busId") int busId){
		return this.busService.viewSchedule(busId);
		
	}
	@PutMapping("/addSeats")
	ResponseEntity<?> addSeats(@RequestParam("count") int count,@RequestParam("busId") int busId){
		return this.busService.addSeats(count,busId);
	}
}
