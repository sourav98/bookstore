package com.cg.bookstore.service;

import com.cg.bookstore.entities.User;

import java.util.List;

public interface IUserService
{
	public User addUser(User user) ;
	public User deleteUser(int userId) ;
	public User getById(int userId) ;
	public List<User> getAllUsers() ;
}
