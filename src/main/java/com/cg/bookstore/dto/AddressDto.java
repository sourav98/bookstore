package com.cg.bookstore.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int addressId;
	private int customerId;
	private String fullName;
	private String address;
	private String city;
	private String country;
	private String pincode;
	
}
