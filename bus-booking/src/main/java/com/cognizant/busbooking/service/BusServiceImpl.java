package com.cognizant.busbooking.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognizant.busbooking.data.BookingServiceClient;
import com.cognizant.busbooking.data.TicketServiceClient;
import com.cognizant.busbooking.entity.Bus;
import com.cognizant.busbooking.model.Booking;
import com.cognizant.busbooking.model.BusFoundModel;
import com.cognizant.busbooking.model.PassengerModel;
import com.cognizant.busbooking.model.ResponseModel;
import com.cognizant.busbooking.model.RouteModel;
import com.cognizant.busbooking.model.Schedule;
import com.cognizant.busbooking.model.TimingModel;
import com.cognizant.busbooking.repository.BusRepository;

@Service
public class BusServiceImpl implements BusService{

	@Autowired
	private BusRepository busRespository;
	
	@Autowired
	private TicketServiceClient ticketServiceClient;
	
	@Autowired
	private BookingServiceClient bookingServiceClient;
	
	
	@Override
	public ResponseEntity<?> findBus(String source, String destination, LocalDate date) {
		ArrayList<Bus> allBus=this.busRespository.findBus();
		ArrayList<Bus> busList=new ArrayList<>();
		ArrayList<RouteModel> routes=new ArrayList<>();
		HashSet<Integer> set=new HashSet<>();
		for(int i=0;i<allBus.size();i++) {
			routes.add(new RouteModel(allBus.get(i).getBusId(),allBus.get(i).getRoute(),allBus.get(i).getTimings()));
		}
		for(int i=0;i<routes.size();i++) {
			boolean first=false;
			RouteModel routeModel=routes.get(i);
			String route=routeModel.getRoute();
			StringBuilder sb=new StringBuilder();
			for(int j=0;j<route.length();j++) {
				if(route.charAt(j)==',') {
					if(sb.toString().equals(source)) {
						first=true;
						sb.setLength(0);
						continue;
					}else if(sb.toString().equals(destination)) {
						if(first==true) {
							set.add(routeModel.getId());
							break;
						}else {
							break;
						}
					}else {
						sb.setLength(0);
					}
				}else {
					sb.append(route.charAt(j));
				}
			}
			if(sb.length()!=0) {
				if(sb.toString().equals(destination) && first==true) {
					set.add(routeModel.getId());
				}
			}
			
		}
		for(int i=0;i<allBus.size();i++) {
			if(set.contains(allBus.get(i).getBusId())){
				if(allBus.get(i).getDate().equals(date)) {
					busList.add(allBus.get(i));
				}
			}
		}
		if(busList==null || busList.size()==0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseModel("No Bus Found"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new BusFoundModel(busList));
	}

	@Override
	public ResponseEntity<?> bookTicket(int busId,int seatsToBeBooked, ArrayList<String> passengerNames,String source,String destination,String userId) {
		int availableSeats=this.busRespository.findAvailableSeats(busId);
		int totalSeats=this.busRespository.findTotalSeats(busId);
		LocalDate date=this.busRespository.findDate(busId);
		if(availableSeats<seatsToBeBooked) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseModel("Enough Seats are Not Available for Booking"));
		}
		ArrayList<Integer> seatNumbers=new ArrayList<>();
		int data=0;
		for(int i=0;i<passengerNames.size();i++) {
			Random r = new Random();
			int low = 1;
			int high = totalSeats;
			int result = r.nextInt(high-low) + low;
			seatNumbers.add(result);
			data=result;
		}
		
		int fare=data*5;
		String bookingId=UUID.randomUUID().toString();
		this.ticketServiceClient.addPassenger(passengerNames,seatNumbers,source,destination,fare,bookingId,busId);
		this.bookingServiceClient.addBooking(bookingId,userId,passengerNames.size(),date,"Active");
		this.busRespository.decreaseAvailableSeats(busId,seatsToBeBooked);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Congratulations Seats Booked with Seat No :"+seatNumbers));
	}

	@Override
	public ResponseEntity<?> viewSchedule(int busId) {
		Bus bus=this.busRespository.findByBusId(busId);
		if(bus==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Can not find Bus"));
		}
		StringBuilder sb=new StringBuilder();
		ArrayList<Schedule> schedule=new ArrayList<>();
		String timings=bus.getTimings();
		String stopName=bus.getRoute();
		ArrayList<String> list=new ArrayList<>();
		ArrayList<LocalTime> list2=new ArrayList<>();
		for(int i=0;i<stopName.length();i++) {
			if(stopName.charAt(i)==',') {
				list.add((sb.toString()));
				sb.setLength(0);
			}else {
				sb.append(stopName.charAt(i));
			}
		}
		if(sb.length()!=0) {
			list.add(sb.toString());
		}
		sb.setLength(0);
		for(int i=0;i<timings.length();i++) {
			if(timings.charAt(i)==',') {
				list2.add(LocalTime.parse(sb.toString()));
				sb.setLength(0);
			}else {
				sb.append(timings.charAt(i));
			}
		}
		if(sb.length()!=0) {
			list2.add(LocalTime.parse(sb.toString()));
		}
		for(int i=0;i<list.size();i++) {
			schedule.add(new Schedule(list.get(i),list2.get(i)));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new TimingModel(schedule));
	}

	@Override
	public ResponseEntity<?> addSeats(int count, int busId) {
		try {
			this.busRespository.addSeats(busId,count);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Seats Increased Successfully"));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel("Something Went Wrong"));
		}
	}

}
