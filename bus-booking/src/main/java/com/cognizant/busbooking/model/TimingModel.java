package com.cognizant.busbooking.model;

import java.util.ArrayList;

public class TimingModel {
	
	ArrayList<Schedule> schedule;

	public TimingModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TimingModel(ArrayList<Schedule> schedule) {
		super();
		this.schedule=schedule;
	}

	public ArrayList<Schedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(ArrayList<Schedule> schedule) {
		this.schedule = schedule;
	}

	
}
