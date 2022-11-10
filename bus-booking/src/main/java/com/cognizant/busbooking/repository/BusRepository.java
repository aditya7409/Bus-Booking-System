package com.cognizant.busbooking.repository;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cognizant.busbooking.entity.Bus;

public interface BusRepository extends CrudRepository<Bus, Integer> {
	
	@Query(value = "SELECT b FROM Bus b")
	ArrayList<Bus> findBus();

	@Query(value="SELECT b.availableSeats from Bus b where b.busId = :busId")
	int findAvailableSeats(@Param("busId") int busId);
	
	@Query(value="SELECT b.totalSeats from Bus b where b.busId = :busId")
	int findTotalSeats(@Param("busId") int busId);

	@Query(value="SELECT b.date from Bus b where b.busId = :busId")
	LocalDate findDate(@Param("busId") int busId);
	
	@Query(value="SELECT b from Bus b where b.busId = :busId")
	Bus findByBusId(@Param("busId") int busId);

	@Transactional
	@Modifying
	@Query("update Bus b set b.availableSeats = b.availableSeats - :seatsToBeBooked where b.busId = :busId")
	void decreaseAvailableSeats(@Param("busId")int busId,@Param("seatsToBeBooked")int seatsToBeBooked);
	
	@Transactional
	@Modifying
	@Query("update Bus b set b.availableSeats = b.availableSeats + :seatsToBeBooked where b.busId = :busId")
	void addSeats(@Param("busId")int busId,@Param("seatsToBeBooked")int seatsToBeBooked);
}
