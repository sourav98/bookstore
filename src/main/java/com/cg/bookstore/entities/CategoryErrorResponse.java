package com.cg.bookstore.entities;

import lombok.Data;

@Data
public class CategoryErrorResponse {
	private int status;
	private String message;
	private long timeStamp;
	
	
}
