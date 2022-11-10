package com.cognizant.Audit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Audit {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String serviceName;
	private String ip;
	private long reqTime;
	private long resTime;
	private long processingTime;
	private String user;
	private int responseCode;
	public Audit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Audit( String serviceName, String ip, long reqTime, long resTime, long processingTime, String user,
			int responseCode) {
		super();
		this.serviceName = serviceName;
		this.ip = ip;
		this.reqTime = reqTime;
		this.resTime = resTime;
		this.processingTime = processingTime;
		this.user = user;
		this.responseCode = responseCode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public long getReqTime() {
		return reqTime;
	}
	public void setReqTime(long reqTime) {
		this.reqTime = reqTime;
	}
	public long getResTime() {
		return resTime;
	}
	public void setResTime(long resTime) {
		this.resTime = resTime;
	}
	public long getProcessingTime() {
		return processingTime;
	}
	public void setProcessingTime(long processingTime) {
		this.processingTime = processingTime;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	
	
	
	
}
