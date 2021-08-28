package com.cg.bookstore.exception;

public class ReviewNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReviewNotFoundException() {
		super();
		
	}

	public ReviewNotFoundException(String message) {
		super(message);
		
	}

	public ReviewNotFoundException(Throwable cause) {
		super(cause);
		
	}

}
