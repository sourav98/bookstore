package com.cg.bookstore.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.dto.RegisterDto;
import com.cg.bookstore.dto.SignInDto;
import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.exception.InvalidCredentialsException;
import com.cg.bookstore.service.CustomerServiceImpl;
import com.cg.bookstore.service.ICustomerService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CustomerController {
	
	@Autowired
	CustomerServiceImpl customerServ;
	
	@PostMapping("/customer")
	public Customer registerCustomer(@RequestBody RegisterDto c)
	{
		return customerServ.registerCustomer(c);
	}
	
	@GetMapping("/customer/{customerId}")
	public Customer getById(@PathVariable("customerId") int customerId)
	{
		return customerServ.getCustomerById(customerId);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<Customer> signIn(@RequestBody SignInDto signInDto) throws InvalidCredentialsException {
		return new ResponseEntity<>(customerServ.signIn(signInDto), HttpStatus.OK);
	}
	
	@PutMapping("/signout/{email}")
	public ResponseEntity<Customer> signOut(@PathVariable("email") String email) throws InvalidCredentialsException {
		customerServ.signOut(email);
		return ResponseEntity.noContent().build();
	}
}
