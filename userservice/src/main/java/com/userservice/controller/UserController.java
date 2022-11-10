package com.userservice.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;

import com.userservice.model.BookBus;
import com.userservice.model.FindBus;
import com.userservice.model.UnAuthorizedAccess;
import com.userservice.model.User;
import com.userservice.services.UserService;
import com.userservice.shared.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {

	/*Injecting the UserService class object here*/
	@Autowired
	private UserService userService;
	
	/* This method allows a new user to signup.If the given credentials is already signed up it will show that user already exists 
	 * The Api end point is -> http://localhost:8717/user-ms/users
	 * */
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user) {
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto=modelMapper.map(user, UserDto.class);
		userDto.setRole("ROLE_USER");
		ResponseEntity<?> responseEntity=this.userService.createUser(userDto);
		return responseEntity;
	}
	
	@GetMapping("/findBus")
	public ResponseEntity<?> findBus(@RequestBody FindBus bus,HttpServletRequest request){
		String source=bus.getSource();
		String destination=bus.getDestination();
		LocalDate localDate=(bus.getDate());
		String ipAddress=request.getRemoteAddr();
		return this.userService.findBus(source,destination,localDate,ipAddress);
		
	}
	
	@PostMapping("/bookTicket")
	public ResponseEntity<?> bookTicket(@RequestBody BookBus bookBus,@RequestHeader("Authorization") String authHeader,HttpServletRequest request){
		UserDto userDto=userService.getUserByUserId(bookBus.getUserId());
		String jwttoken=authHeader.substring(7);
		if(!userDto.getJwt().equals(jwttoken)){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UnAuthorizedAccess("You Don't have permissions to access this page"));
		}
		String ipAddress=request.getRemoteAddr();
		int seatsToBeBooked=bookBus.getPassengerNames().size();
		return this.userService.bookTicket(bookBus.getBusId(),seatsToBeBooked,bookBus.getPassengerNames(),bookBus.getSource(),bookBus.getDestination(),bookBus.getUserId(),ipAddress);
	}
	@GetMapping("/viewSchedule/{id}")
	public ResponseEntity<?> viewSchedule(@PathVariable("id") String id,HttpServletRequest request){
		int busId=Integer.parseInt(id);
		String ipAddress=request.getRemoteAddr();
		return this.userService.viewSchedule(busId,ipAddress);
		
	}
	
	@GetMapping("/upcomingBookings/{userId}")
	public ResponseEntity<?> upcomingBookings(@PathVariable("userId") String userId,@RequestHeader("Authorization") String authHeader,HttpServletRequest request) {
		UserDto userDto=userService.getUserByUserId(userId);
		String jwttoken=authHeader.substring(7);
		if(!userDto.getJwt().equals(jwttoken)){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UnAuthorizedAccess("You Don't have permissions to access this page"));
		}
		String ipAddress=request.getRemoteAddr();
		return this.userService.findUpcomingBookings(userId,ipAddress);
	}
	
	@GetMapping("/pastBookings/{userId}")
	public ResponseEntity<?> pastBookings(@PathVariable("userId") String userId,@RequestHeader("Authorization") String authHeader,HttpServletRequest request) {
		UserDto userDto=userService.getUserByUserId(userId);
		String jwttoken=authHeader.substring(7);
		if(!userDto.getJwt().equals(jwttoken)){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UnAuthorizedAccess("You Don't have permissions to access this page"));
		}
		String ipAddress=request.getRemoteAddr();
		return this.userService.findPastBookings(userId,ipAddress);
	}
	@PutMapping("/cancelBooking/{userId}/{bookingId}")
	public ResponseEntity<?> cancelBooking(@PathVariable("bookingId") String bookingId,@PathVariable("userId") String userId,@RequestHeader("Authorization") String authHeader,HttpServletRequest request){
		UserDto userDto=userService.getUserByUserId(userId);
		String jwttoken=authHeader.substring(7);
		if(!userDto.getJwt().equals(jwttoken)){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UnAuthorizedAccess("You Don't have permissions to access this page"));
		}
		String ipAddress=request.getRemoteAddr();
		return this.userService.cancelBooking(bookingId,ipAddress);
	}
	
	@GetMapping("/logout/{userId}")
	public void logout(@PathVariable("userId") String userId) {
		this.userService.updateJwtToken(userId,"");
	}
}

