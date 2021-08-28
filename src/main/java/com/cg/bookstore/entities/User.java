package com.cg.bookstore.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "users")
@Data
public class User {
	
	@Id
	@GeneratedValue
	private int userId;
	private String email;
	private String password;
	private String role;
}
