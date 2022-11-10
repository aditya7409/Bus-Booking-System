package com.userservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bus {
	
	@Id
	private int busId;
	private String busName;
	private int totalSeats;
	private int availableSeats;
	private String route;
	private String timings;
	private String status;
	
	public Bus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bus(int busId, String busName, int totalSeats, int availableSeats, String route, String timings,
			String status) {
		super();
		this.busId = busId;
		this.busName = busName;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.route = route;
		this.timings = timings;
		this.status = status;
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getTimings() {
		return timings;
	}

	public void setTimings(String timings) {
		this.timings = timings;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
