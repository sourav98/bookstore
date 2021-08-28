package com.cg.bookstore.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValidBookOrderErrorResponse {
	
	private int status;
	private String message;
	private Date timeStamp;
	private String details;

}
