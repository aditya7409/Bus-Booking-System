package com.cognizant.busbooking.model;

public class RouteModel {
	
	private int id;
	private String route;
	private String timings;
	
	public RouteModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RouteModel(int id, String route,String timings) {
		super();
		this.id = id;
		this.route = route;
		this.timings=timings;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	
	
}
