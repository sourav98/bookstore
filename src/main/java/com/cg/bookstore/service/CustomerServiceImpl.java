package com.cg.bookstore.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.dto.RegisterDto;
import com.cg.bookstore.dto.SignInDto;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.exception.InvalidCredentialsException;
import com.cg.bookstore.repository.ICustomerRepository;


@Service
public class CustomerServiceImpl implements ICustomerService {
	
	@Autowired
	ICustomerRepository customerRepo ;
	@Override
	public Customer registerCustomer(RegisterDto c) {
		Customer c1=new Customer();
		c1.setEmail(c.getEmail()); 
		c1.setFullName(c.getFullName());
		c1.setPassword(c.getPassword());
		return customerRepo.save(c1) ;
	}

	@Override
	public Customer getCustomerById(int customerId) {
		Optional<Customer> cs = customerRepo.findById(customerId) ;
		return cs.get();
	}

	@Override
	public Customer signIn(SignInDto signInDto) throws InvalidCredentialsException {
		Optional <Customer> opt=customerRepo.findByEmail(signInDto.getEmail());
		
		if(opt.isPresent()) {
			Customer c=opt.get();
			if( c.getEmail().equals(signInDto.getEmail()) && c.getPassword().equals(signInDto.getPassword())) {
				c.setLoggedIn(true);
				customerRepo.save(c);
			}
			else {
				throw new InvalidCredentialsException("Enter valid Credentials");
			}
			
			return c;
		} else {
			throw new InvalidCredentialsException("Enter valid Credentials");
		}

	}

	@Override
	public void signOut(String email) {
		Optional <Customer> opt=customerRepo.findByEmail(email);
		if(opt.isPresent()) {
			Customer c=opt.get();
			c.setLoggedIn(false);
			customerRepo.save(c);
		}

	}

}
