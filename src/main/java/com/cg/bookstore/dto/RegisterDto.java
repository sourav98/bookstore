package com.cg.bookstore.dto;

import javax.persistence.GeneratedValue;

import lombok.Data;

@Data
public class RegisterDto {
	
	
	private String fullName;
	private String email;
	private String password;
}
