package com.cg.bookstore.exception;

public class CategoryExistenceException extends RuntimeException{

	public CategoryExistenceException() {
		super();
	}
	
	public CategoryExistenceException(String message) {
		super(message);
	}

	public CategoryExistenceException(Throwable cause) {
		super(cause);
	}
	

}
