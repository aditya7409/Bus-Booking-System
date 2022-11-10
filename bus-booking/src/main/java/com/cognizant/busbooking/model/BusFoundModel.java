package com.cognizant.busbooking.model;

import java.util.List;

import com.cognizant.busbooking.entity.Bus;

public class BusFoundModel {

	private List<Bus> busList;

	public BusFoundModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusFoundModel(List<Bus> busList) {
		super();
		this.busList = busList;
	}

	public List<Bus> getBusList() {
		return busList;
	}

	public void setBusList(List<Bus> busList) {
		this.busList = busList;
	}
	
	
}
