package com.cognizant.booking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.cognizant.booking.data.TicketServiceClient;
import com.cognizant.booking.entity.Booking;
import com.cognizant.booking.model.BookingModel;
import com.cognizant.booking.model.ResponseModel;
import com.cognizant.booking.repository.BookingRepository;
@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private TicketServiceClient ticketServiceClient;
	
	@Override
	public void addBooking(Booking book) {
		System.out.println("Booking service impl");
		this.bookingRepository.save(book);
	}

	@Override
	public ResponseEntity<?> findUpcomingBookings(String userId) {
		LocalDate date=LocalDate.now();
		HashSet<String> bookingId=this.bookingRepository.findBookingId(userId);
		ArrayList<BookingModel> list=new ArrayList<>();
		for(String s:bookingId) {
			Booking upcomingBookings=this.bookingRepository.findUpcomingBookings(s,date);
			if(upcomingBookings==null || upcomingBookings.getStatus().equals("Cancelled")) {
				continue;
			}
			ArrayList<String> passengerNames=(ArrayList<String>) this.ticketServiceClient.getTicketDetails(s).getBody();
			list.add(new BookingModel(upcomingBookings,passengerNames));
		}
		if(list==null || list.size()==0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseModel("No Upcoming Bookings"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	@Override
	public ResponseEntity<?> findPastBookings(String userId) {
		LocalDate date=LocalDate.now();
		HashSet<String> bookingId=this.bookingRepository.findBookingId(userId);
		ArrayList<BookingModel> list=new ArrayList<>();
		for(String s:bookingId) {
			Booking upcomingBookings=this.bookingRepository.findPastBookings(s,date);
			if(upcomingBookings==null || upcomingBookings.getStatus().equals("Cancelled")) {
				continue;
			}
			ArrayList<String> passengerNames=(ArrayList<String>) this.ticketServiceClient.getTicketDetails(s).getBody();
			list.add(new BookingModel(upcomingBookings,passengerNames));
		}
		if(list==null || list.size()==0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseModel("No Upcoming Bookings"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@Override
	public ResponseEntity<?> cancelBooking(String bookingId) {
		try {
			this.bookingRepository.cancelBooking(bookingId,"Cancelled");
			this.ticketServiceClient.cancelBooking(bookingId);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Booking "+bookingId+" Cancelled Successfully"));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseModel("Something Went Wrong"));
		}
	}

}
