package com.cognizant.ticket.model;

import java.util.ArrayList;

public class PassengerModel {
	
	private ArrayList<String> passengerNames;
	private ArrayList<Integer> seatsNo;
	private String source;
	private String destination;
	private int fare;
	private String bookingId;
	
	public PassengerModel(ArrayList<String> passengerNames, ArrayList<Integer> seatsNo, String source,
			String destination, int fare,String bookingId) {
		super();
		this.passengerNames = passengerNames;
		this.seatsNo = seatsNo;
		this.source = source;
		this.destination = destination;
		this.fare = fare;
		this.bookingId=bookingId;
	}
	
	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public ArrayList<String> getPassengerNames() {
		return passengerNames;
	}
	public void setPassengerNames(ArrayList<String> passengerNames) {
		this.passengerNames = passengerNames;
	}
	public ArrayList<Integer> getSeatsNo() {
		return seatsNo;
	}
	public void setSeatsNo(ArrayList<Integer> seatsNo) {
		this.seatsNo = seatsNo;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
	
	
}