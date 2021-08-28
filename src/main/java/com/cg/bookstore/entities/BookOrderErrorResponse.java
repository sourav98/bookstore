package com.cg.bookstore.entities;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookOrderErrorResponse {
	
	private int status;
	private String message;
	private LocalDateTime timeStamp;

}
