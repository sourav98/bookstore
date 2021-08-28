package com.cg.bookstore.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.User;
import com.cg.bookstore.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService
{
	 @Autowired
     IUserRepository userRepo ;
	 
	 final static Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
	
	@Override
	public User addUser(User user)
	{
		LOGGER.info("Adding user using user Service implementation");
		return userRepo.save(user) ;
	}

	@Override
	public User deleteUser(int userId)
	{
		LOGGER.info("Deleting user using user Service implementation");
		Optional<User> user = userRepo.findById(userId) ;
		userRepo.deleteById(userId);
		return user.get() ;
		
	}

	@Override
	public User getById(int userId)
	{
		LOGGER.info("Getting user using userId Service implementation");
 		Optional<User> user = userRepo.findById(userId) ;
        return user.get() ;		
	}

	@Override
	public List<User> getAllUsers()
	{
		LOGGER.info("Getting all users using Service implementation");
		List<User> users = userRepo.findAll() ;
		return users ;
	}
	
	

}




