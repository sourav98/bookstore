package com.cg.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class BookOrderFoundException extends RuntimeException{

	public BookOrderFoundException() {
		super();
		
	}

	public BookOrderFoundException(String message) {
		super(message);
		
	}

	public BookOrderFoundException(Throwable cause) {
		super(cause);
	}
	

}
