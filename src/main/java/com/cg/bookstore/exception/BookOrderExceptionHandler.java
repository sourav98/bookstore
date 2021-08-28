package com.cg.bookstore.exception;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.bookstore.entities.BookOrderErrorResponse;
import com.cg.bookstore.entities.ValidAddressErrorResponse;
import com.cg.bookstore.entities.ValidBookOrderErrorResponse;


@ControllerAdvice
public class BookOrderExceptionHandler {

	//for error 404 NOT FOUND
	@ExceptionHandler
	public ResponseEntity<BookOrderErrorResponse> handleException(BookOrderNotFoundException exception){
		
		BookOrderErrorResponse error = new BookOrderErrorResponse();
		//setting 404 i.e. NOT OFUND
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		
		
		
	}
	//for error 409 conflict
	@ExceptionHandler
	public ResponseEntity<BookOrderErrorResponse> handleException(BookOrderFoundException exception){
		
		BookOrderErrorResponse error = new BookOrderErrorResponse();
		//setting CONFLICT i.e. 409 
		error.setStatus(HttpStatus.CONFLICT.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error,HttpStatus.CONFLICT);
		
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> addValidationException(MethodArgumentNotValidException exception){
		ValidBookOrderErrorResponse response = new ValidBookOrderErrorResponse(HttpStatus.BAD_REQUEST.value(),"Validation Error", new Date(),exception.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);  //400 Bad Request
		
	}
}
