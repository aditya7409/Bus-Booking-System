package com.userservice.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Booking {

	@Id
	private String bookingId;
	private  int userId;
	private int seatsBooked;
	private LocalDate date;
	private String status;
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Booking(String bookingId, int userId, int seatsBooked, LocalDate date, String status) {
		super();
		this.bookingId = bookingId;
		this.userId = userId;
		this.seatsBooked = seatsBooked;
		this.date = date;
		this.status = status;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getSeatsBooked() {
		return seatsBooked;
	}
	public void setSeatsBooked(int seatsBooked) {
		this.seatsBooked = seatsBooked;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

