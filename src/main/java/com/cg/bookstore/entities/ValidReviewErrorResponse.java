package com.cg.bookstore.entities;

import java.util.Date;
import lombok.Data;

@Data
public class ValidReviewErrorResponse {
	private int status;
	private String message;
	private Date timeStamp;
	private String details;
	public ValidReviewErrorResponse() {
		super();
	}
	public ValidReviewErrorResponse(int status, String message, Date timeStamp, String details) {
		super();
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
		this.details = details;
	}	@Override
	public String toString() {
		return "ValidReviewErrorResponse [status=" + status + ", message=" + message + ", timeStamp=" + timeStamp
				+ ", details=" + details + "]";
	}
	
	
}
