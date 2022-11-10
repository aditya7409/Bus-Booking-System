package com.cognizant.ticket.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognizant.ticket.data.BusServiceClient;
import com.cognizant.ticket.entity.Ticket;
import com.cognizant.ticket.model.ResponseModel;

import com.cognizant.ticket.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired BusServiceClient busServiceClient;
	
	@Override
	public void addPassenger(List<String> passengerNames, List<Integer> seatNo, String source, String destination,int fare, String bookingId,int busId) {
		
			for(int i=0;i<passengerNames.size();i++) {
				Ticket ticket=new Ticket(seatNo.get(i),passengerNames.get(i),source,destination,fare,bookingId,busId);
				this.ticketRepository.save(ticket);
			}
		
	}
	@Override
	public ResponseEntity<?> findTicket(String bookingId) {
		ArrayList<String> tickets=this.ticketRepository.findTicket(bookingId);
		if(tickets==null || tickets.size()==0) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseModel("No Tickets Found"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(tickets);
	}
	@Override
	public ResponseEntity<?> cancelTickets(String bookingId) {
		try {
			int busId=this.ticketRepository.findBusId(bookingId);
			System.out.println(busId+" bus");
			int count=this.ticketRepository.cancelTickets(bookingId);
			this.busServiceClient.addSeats(count,busId);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Tickets Cancelled Successfully"));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel("Something Went Wrong While Cancelling the Tickets !! Please Try Again"));
		}
	}
	
}
