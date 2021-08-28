package com.cg.bookstore.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookOrderNotFoundException extends RuntimeException{

	public BookOrderNotFoundException() {
		super();
		
	}

	public BookOrderNotFoundException(String message) {
		super(message);
		
	}

	public BookOrderNotFoundException(Throwable cause) {
		super(cause);
		
	}
	
	

}
