package com.cg.bookstore.service;

import com.cg.bookstore.entities.User;

public interface ILoginService {

	public User addUser(User user);
	public User removeUser(User user);
	public User validateUser(User user);
}
