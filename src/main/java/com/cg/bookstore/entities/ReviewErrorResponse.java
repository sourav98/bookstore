package com.cg.bookstore.entities;

import java.util.Date;

public class ReviewErrorResponse {
	private int status;
	private String message;
	private long timeStamp;
	public ReviewErrorResponse() {}
	public ReviewErrorResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
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
		return "ReviewErrorResponse [status=" + status + ", message=" + message + ", timeStamp=" + timeStamp + "]";
	}
	
		
}
