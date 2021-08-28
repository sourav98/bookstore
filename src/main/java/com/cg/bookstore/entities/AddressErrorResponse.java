package com.cg.bookstore.entities;

import java.util.Date;

public class AddressErrorResponse {
	private int status;
	private String message;
	private long timeStamp;
	
	public AddressErrorResponse() {}
	public AddressErrorResponse(int status, String message) {
		super();
		this.status=status;
		this.message=message;
		this.timeStamp=new Date().getTime();
		
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "AddressErrorResponse [status=" + status + ", message=" + message + ", timeStamp=" + timeStamp + "]";
	}
	
	
}
