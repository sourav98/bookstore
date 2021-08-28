package com.cg.bookstore.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.cg.bookstore.entities.BookErrorResponse;
import com.cg.bookstore.entities.DuplicateBookErrorResponse;
import com.cg.bookstore.entities.ValidBookErrorResponse;

@ControllerAdvice
public class BookExceptionHandler
{
	@ExceptionHandler
	public ResponseEntity<BookErrorResponse> handleException(BookNotFoundException exception)
	{
		BookErrorResponse error = new BookErrorResponse();

		error.setStatus(HttpStatus.NOT_FOUND.value()); // Value is 404
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); // 404 Error Code

	}

	@ExceptionHandler
	public ResponseEntity<DuplicateBookErrorResponse> handleDuplicateBookException(DuplicateBookException exception)
	{
		DuplicateBookErrorResponse error = new DuplicateBookErrorResponse();

		error.setStatus(HttpStatus.NOT_FOUND.value()); // Value is 404
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); // 404 Error Code

	}

	// Handling Book Add Validation
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> addValidationException(MethodArgumentNotValidException exception)
	{
		ValidBookErrorResponse response = new ValidBookErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation Error",
				LocalDate.now(), exception.getBindingResult().getFieldError().getDefaultMessage());

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // Validations Failing
	}

}
