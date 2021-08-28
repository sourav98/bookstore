package com.cg.bookstore.entities;




import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;


@Entity
@Data
public class Customer {

	@Id
	@GeneratedValue
	private int customerId;
	private String fullName;
	private String email;
	private String password;
	private String role="customer";
	private boolean isLoggedIn=false;
	private boolean isAdmin=false;
}
