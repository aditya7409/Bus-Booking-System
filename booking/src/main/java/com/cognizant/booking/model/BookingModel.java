package com.cognizant.booking.model;

import java.util.ArrayList;

import com.cognizant.booking.entity.Booking;

public class BookingModel {

	private Booking bookings;
	private ArrayList<String> passengerNames;
	public BookingModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookingModel(Booking bookings,ArrayList<String> passengerNames) {
		super();
		this.bookings = bookings;
		this.passengerNames=passengerNames;
	}

	public Booking getBookings() {
		return bookings;
	}

	public void setBookings(Booking bookings) {
		this.bookings = bookings;
	}

	public ArrayList<String> getPassengerNames() {
		return passengerNames;
	}

	public void setPassengerNames(ArrayList<String> passengerNames) {
		this.passengerNames = passengerNames;
	}
	
	
}
