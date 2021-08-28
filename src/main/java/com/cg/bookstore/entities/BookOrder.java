package com.cg.bookstore.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;


@Data
@Entity
@AllArgsConstructor
public class BookOrder {
 
	public BookOrder() {
		// TODO Auto-generated constructor stub
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq" )
	@SequenceGenerator(name = "seq" , initialValue = 10000 , allocationSize = 10)
	private int orderId;
	
	//getting present local date for whenever the order is added 
	private LocalDate orderDate = LocalDate.now();
	
	private double orderTotal;
	
	
	private String status;
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE , CascadeType.REFRESH})
    @JoinColumn(name = "address_id_fk" , unique = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Address shippingAddress;
	
	
	private String paymentMethod;
	
	
	@Size(min = 4, max = 30 ,message ="Name should be between 4 and 30 characters")
	private String recipientName;
	private String recipientPhone;
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE , CascadeType.REFRESH})
    @JoinColumn(name = "customer_id_fk" , unique = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Customer customer;
	
	
	
	
}
