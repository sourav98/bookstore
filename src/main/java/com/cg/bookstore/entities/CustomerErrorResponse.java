package com.cg.bookstore.entities;

import lombok.Data;

//for getters, setters, constructors and toString
@Data
public class CustomerErrorResponse {
	
		private int status;
		private String message;
		private long timestamp;

}
