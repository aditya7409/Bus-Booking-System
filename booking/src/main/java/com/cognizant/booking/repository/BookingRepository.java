package com.cognizant.booking.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cognizant.booking.entity.Booking;

public interface BookingRepository extends CrudRepository<Booking, String> {
	
	@Query(value="SELECT b from Booking b where b.bookingId = :bookingId and b.date >= :currentDate")
	Booking findUpcomingBookings(@Param("bookingId") String bookingId,@Param("currentDate") LocalDate currentDate);
	
	@Query(value="SELECT b from Booking b where b.bookingId = :bookingId and b.date <= :currentDate")
	Booking findPastBookings(@Param("bookingId") String bookingId,@Param("currentDate") LocalDate currentDate);
	
	@Query(value="SELECT b.bookingId from Booking b where b.userId = :userId")
	HashSet<String> findBookingId(@Param("userId") String userId);
	
	@Transactional
	@Modifying
	@Query("update Booking b set b.status = :status where b.bookingId = :bookingId")
	void cancelBooking(@Param("bookingId")String bookingId,@Param("status") String status);
}


