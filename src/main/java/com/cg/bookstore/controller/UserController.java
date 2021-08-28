package com.cg.bookstore.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.User;
import com.cg.bookstore.service.UserServiceImpl;

import java.util.List;


@RestController
public class UserController
{
	final static Logger LOGGER = LogManager.getLogger(UserController.class);
	
	@Autowired
	UserServiceImpl userSer ;
	
	@PostMapping("/user")
	public User addUser(@RequestBody User user)
	{
		LOGGER.info("Adding user using user controller");
		return userSer.addUser(user) ;
	}
	
	@GetMapping("/user/all")
	public List<User> getAllUsers()
	{
		LOGGER.info("Getting user list using User controller");
		return userSer.getAllUsers() ;
	}
	
	@GetMapping("/user/id/{userId}")
	public User getById(@PathVariable("userId") int userId)
	{
		LOGGER.info("Getting particular user using user controller");
		return userSer.getById(userId) ;
	}
	
	@DeleteMapping("/user/delete/{userId}")
	public User deleteById(@PathVariable("userId") int userId)
	{
		LOGGER.info("Deleting particular user using user controller");
		return userSer.deleteUser(userId) ;
	}
}


