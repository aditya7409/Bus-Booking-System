package com.cognizant.busbooking.model;

import java.time.LocalTime;

public class Schedule {

	private String stopName;
	private LocalTime time;
	public Schedule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Schedule(String stopName, LocalTime time) {
		super();
		this.stopName = stopName;
		this.time = time;
	}
	public String getStopName() {
		return stopName;
	}
	public void setStopName(String stopName) {
		this.stopName = stopName;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	
}
