package com.userservice.model;

import java.util.ArrayList;



public class BookBus {
	
	private int busId;
	int seatsToBeBookd;
	private ArrayList<String> passengerNames;
	private String source;
	private String destination;
	private String userId;
	public BookBus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookBus(int busId, int seatsToBeBookd, ArrayList<String> passengerNames, String source, String destination,
			String userId) {
		super();
		this.busId = busId;
		this.seatsToBeBookd = seatsToBeBookd;
		this.passengerNames = passengerNames;
		this.source = source;
		this.destination = destination;
		this.userId = userId;
	}
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	public int getSeatsToBeBookd() {
		return seatsToBeBookd;
	}
	public void setSeatsToBeBookd(int seatsToBeBookd) {
		this.seatsToBeBookd = seatsToBeBookd;
	}
	public ArrayList<String> getPassengerNames() {
		return passengerNames;
	}
	public void setPassengerNames(ArrayList<String> passengerNames) {
		this.passengerNames = passengerNames;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
