package com.cg.bookstore.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.bookstore.entities.CategoryErrorResponse;
import com.cg.bookstore.entities.ValidCategoryErrorResponse;

@ControllerAdvice
public class CategoryExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<CategoryErrorResponse> handleException(CategoryExistenceException exception){
		CategoryErrorResponse err = new CategoryErrorResponse();
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setMessage(exception.getMessage());
		err.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND); //404 not found
	}
	
	@ExceptionHandler
	public ResponseEntity<?> addValidationException(MethodArgumentNotValidException exception){
		ValidCategoryErrorResponse res = new ValidCategoryErrorResponse(HttpStatus.BAD_REQUEST.value(),"Validation Error",new Date(),exception.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
	}
	
}
