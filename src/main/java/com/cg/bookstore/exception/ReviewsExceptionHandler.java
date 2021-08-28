package com.cg.bookstore.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.cg.bookstore.entities.ReviewErrorResponse;
import com.cg.bookstore.entities.ValidReviewErrorResponse;

	@ControllerAdvice
public class ReviewsExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<ReviewErrorResponse> handleException(ReviewNotFoundException exception) {
		ReviewErrorResponse error = new ReviewErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);  //404 Not found
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> addValidationException(MethodArgumentNotValidException exception){
		ValidReviewErrorResponse response = new ValidReviewErrorResponse(HttpStatus.BAD_REQUEST.value(),"Validation Error", new Date(),exception.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		
	}
	
}
