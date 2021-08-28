package com.cg.bookstore.service;

import java.util.List;

import com.cg.bookstore.dto.RegisterDto;
import com.cg.bookstore.dto.SignInDto;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;


public interface ICustomerService {

	public Customer registerCustomer(RegisterDto c);
	public Customer getCustomerById(int customerId);
	
	public Customer signIn(SignInDto signInDto);
	public void signOut(String email);
	
}
