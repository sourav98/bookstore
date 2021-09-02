package com.cg.bookstore.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int addressId;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE , CascadeType.REFRESH})
	@JoinColumn(name="customer_id_fk")
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Customer customer;
	
	@NotEmpty
	@Size(min=2,max=30,message="Address Line should be between 2 and 30 characters")
	private String address;
	 
	@NotEmpty
	@Size(min=2,max=20,message="City name should be between 2 and 20 characters")
	private String city;
	
	@NotEmpty
	@Size(min=2,max=20,message="Country name should be between 2 and 20 characters")
	private String country;
	
	@NotEmpty
	@Size(min=6,max=8,message="Pincode should be between 6 and 8 characters")
	private String pincode;


}
