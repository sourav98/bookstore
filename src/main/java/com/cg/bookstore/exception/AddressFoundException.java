package com.cg.bookstore.exception;

public class AddressFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AddressFoundException() {
		super();
		
	}

	public AddressFoundException(String message) {
		super(message);
		
	}

	public AddressFoundException(Throwable cause) {
		super(cause);
		
	}
	
	

}
