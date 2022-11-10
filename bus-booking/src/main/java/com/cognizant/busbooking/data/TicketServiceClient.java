package com.cognizant.busbooking.data;

import java.util.ArrayList;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.busbooking.model.PassengerModel;

@FeignClient(name="ticket-ms")
public interface TicketServiceClient {
	
	@PostMapping("users/addPassenger")
	public void addPassenger(@RequestParam("passengerNames") ArrayList<String> passengerNames,@RequestParam("seatNumbers") ArrayList<Integer> seatNumbers,@RequestParam("source") String source,@RequestParam("destination") String destination,@RequestParam("fare") int fare,@RequestParam("bookingId") String bookingId,@RequestParam("busId") int busId);
	
	
	
	
}
