package com.cg.bookstore.exception;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.bookstore.entities.AddressErrorResponse;
import com.cg.bookstore.entities.ValidAddressErrorResponse;



@ControllerAdvice
public class AddressExceptionHandler {

		
		@ExceptionHandler
		public ResponseEntity<AddressErrorResponse> handleAddressNotFoundException(AddressNotFoundException exception) {
			AddressErrorResponse error = new AddressErrorResponse();
			
			error.setStatus(HttpStatus.NOT_FOUND.value());
			error.setMessage(exception.getMessage());
			error.setTimeStamp(System.currentTimeMillis());
			
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);  //404 Not found
		}
		
		@ExceptionHandler
		public ResponseEntity<AddressErrorResponse> handleAddressFoundException(AddressFoundException exception) {
			AddressErrorResponse error = new AddressErrorResponse();
			
			error.setStatus(HttpStatus.CONFLICT.value());
			error.setMessage(exception.getMessage());
			error.setTimeStamp(System.currentTimeMillis());
			
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);  //409 Conflict
		}
		
		
		//handle address add,update validation
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<?> addValidationException(MethodArgumentNotValidException exception){
			ValidAddressErrorResponse response = new ValidAddressErrorResponse(HttpStatus.BAD_REQUEST.value(),"Validation Error", new Date(),exception.getBindingResult().getFieldError().getDefaultMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);  //400 Bad Request
			
		}
		
}
