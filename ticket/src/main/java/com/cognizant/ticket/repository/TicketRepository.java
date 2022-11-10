package com.cognizant.ticket.repository;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.ticket.entity.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
	
	
	@Query(value="SELECT t.passengerName from Ticket t where t.bookingId = :bookingId")
	ArrayList<String> findTicket(@Param("bookingId") String bookingId);
	
	@Query(value="SELECT t.busId from Ticket t where t.bookingId = :bookingId")
	int findBusId(@Param("bookingId") String bookingId);
	
	@Transactional
	@Modifying
	@Query("delete from Ticket t where t.bookingId = :bookingId")
	int cancelTickets(@RequestParam("bookingId") String bookingId);
	
}
