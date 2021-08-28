package com.cg.bookstore.entities;

import lombok.Data;

@Data
public class OrderDetailsErrorResponse
{
	private int status;
	private String message;
	private long timeStamp;

}
