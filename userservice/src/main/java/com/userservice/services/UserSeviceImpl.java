package com.userservice.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.userservice.data.AuditServiceClient;
import com.userservice.data.BookingServiceClient;
import com.userservice.data.BusServiceClient;

import com.userservice.data.UserEntity;
import com.userservice.model.GeneralResponseModel;
import com.userservice.repository.UserRepository;
import com.userservice.shared.UserDto;

@Service
public class UserSeviceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private BusServiceClient busServiceClient;
	
	@Autowired
	private BookingServiceClient bookingServiceClient;
	
	@Autowired
	private AuditServiceClient auditServiceClient;
	
	@Override
	public ResponseEntity<?> createUser(UserDto userDetails) {
		if(this.userRepository.findByEmail(userDetails.getEmail())!=null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new GeneralResponseModel("User Already Exists"));
		}
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity=modelMapper.map(userDetails, UserEntity.class);
		this.userRepository.save(userEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(new GeneralResponseModel("Signed Up Successfully"));
	}
	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity=this.userRepository.findByUserId(userId);
		if(userEntity==null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		UserDto userDto=new ModelMapper().map(userEntity, UserDto.class);
		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity=this.userRepository.findByEmail(username);
		if(userEntity==null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(userEntity.getEmail(),userEntity.getEncryptedPassword(),true,true,true,true,new ArrayList<>());
	}


	@Override
	public UserDto getUserDetailsByEmail(String email) {
		UserEntity userEntity=this.userRepository.findByEmail(email);
		if(userEntity==null) {
			throw new UsernameNotFoundException(email);
		}
		return new ModelMapper().map(userEntity, UserDto.class);
	}


	


	@Override
	public void updateJwtToken(String userId,String jwtToken) {
		this.userRepository.updateJwtByUserId(jwtToken, userId);
		
	}




	@Override
	public ResponseEntity<?> findBus(String source, String destination, LocalDate date,String ipAddress) {
		long startTime = System.currentTimeMillis();
		ResponseEntity<?> responseEntity= this.busServiceClient.findBus(source,destination,date);
		long endTime = System.currentTimeMillis();
		long duration=endTime-startTime;
		String serviceName="FIND BUS";
		String user="ALL";
		int responseCode=responseEntity.getStatusCodeValue();
		this.auditServiceClient.addDetails(serviceName, ipAddress, startTime, endTime, duration, user, responseCode);
		return responseEntity;
		
	}


	@Override
	public ResponseEntity<?> bookTicket(int busId, int seatsToBeBookd, ArrayList<String> passengerNames, String source,
			String destination, String userId,String ipAddress) {
		long startTime = System.currentTimeMillis();
		ResponseEntity<?> responseEntity=this.busServiceClient.bookTicket(busId,seatsToBeBookd,passengerNames,source,destination,userId);
		long endTime = System.currentTimeMillis();
		long duration=endTime-startTime;
		String serviceName="BOOK TICKET";
		String user=userId;
		int responseCode=responseEntity.getStatusCodeValue();
		this.auditServiceClient.addDetails(serviceName, ipAddress, startTime, endTime, duration, user, responseCode);
		return responseEntity;
	}


	@Override
	public ResponseEntity<?> viewSchedule(int busId,String ipAddress) {
		long startTime = System.currentTimeMillis();
		ResponseEntity<?> responseEntity=this.busServiceClient.viewSchedule(busId);;
		long endTime = System.currentTimeMillis();
		long duration=endTime-startTime;
		String serviceName="VIEW SCHEDULE";
		String user="ALL";
		int responseCode=responseEntity.getStatusCodeValue();
		this.auditServiceClient.addDetails(serviceName, ipAddress, startTime, endTime, duration, user, responseCode);
		return responseEntity;
	}

	@Override
	public ResponseEntity<?> findUpcomingBookings(String userId,String ipAddress) {
		long startTime = System.currentTimeMillis();
		ResponseEntity<?> responseEntity=this.bookingServiceClient.upcomingBookings(userId);;
		long endTime = System.currentTimeMillis();
		long duration=endTime-startTime;
		String serviceName="UPCOMING BOOKINGS";
		String user=userId;
		int responseCode=responseEntity.getStatusCodeValue();
		this.auditServiceClient.addDetails(serviceName, ipAddress, startTime, endTime, duration, user, responseCode);
		return responseEntity;
	}


	@Override
	public ResponseEntity<?> findPastBookings(String userId,String ipAddress) {
		long startTime = System.currentTimeMillis();
		ResponseEntity<?> responseEntity=this.bookingServiceClient.pastBookings(userId);
		long endTime = System.currentTimeMillis();
		long duration=endTime-startTime;
		String serviceName="PAST BOOKINGS";
		String user=userId;
		int responseCode=responseEntity.getStatusCodeValue();
		this.auditServiceClient.addDetails(serviceName, ipAddress, startTime, endTime, duration, user, responseCode);
		return responseEntity;
	}


	@Override
	public ResponseEntity<?> cancelBooking(String bookingId,String ipAddress) {
		long startTime = System.currentTimeMillis();
		ResponseEntity<?> responseEntity= this.bookingServiceClient.cancelBooking(bookingId);
		long endTime = System.currentTimeMillis();
		long duration=endTime-startTime;
		String serviceName="CANCEL BOOKING";
		String user=bookingId;
		int responseCode=responseEntity.getStatusCodeValue();
		this.auditServiceClient.addDetails(serviceName, ipAddress, startTime, endTime, duration, user, responseCode);
		return responseEntity;
	}

}
