package com.userservice.services;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.userservice.shared.UserDto;

public interface UserService extends UserDetailsService {
	ResponseEntity<?> createUser(UserDto userDetails);
	void updateJwtToken(String userId,String jwtToken);
	UserDto getUserDetailsByEmail(String email);
	ResponseEntity<?> findBus(String source,String destination,LocalDate date,String ipAddress);
	ResponseEntity<?> bookTicket(int busId,int seatsToBeBookd,ArrayList<String> passengerNames,String source,String destination,String userId,String ipAddress);
	ResponseEntity<?> viewSchedule(int busId,String ipAddress);
	ResponseEntity<?> findUpcomingBookings(String userId,String ipAddress);
	ResponseEntity<?> findPastBookings(String userId,String ipAddress);
	ResponseEntity<?> cancelBooking(String bookingId,String ipAddress);
	UserDto getUserByUserId(String userId);
}
