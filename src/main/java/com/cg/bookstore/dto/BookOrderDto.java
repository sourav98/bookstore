package com.cg.bookstore.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.cg.bookstore.entities.Address;
import com.cg.bookstore.entities.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookOrderDto {
    
	@NotNull(message = "Order total should be greater than 0")
    private double orderTotal;
	
	@NotEmpty(message = "Status can't be empty")
	private String status;
	
	@NotNull(message ="Address id can't be empty")
	private Integer addressId;
	
	@NotEmpty(message = "payment Method can't be empty")
	private String paymentMethod;
	
	@NotEmpty
	@Size(min = 4, max = 30 ,message ="Name should be between 4 and 30 characters")
	private String recipientName;
	
	@NotEmpty
	@Size(min =10 , max =10 ,message ="Phone number should be of 10 characters")
	private String recipientPhone;

	@NotNull(message = "customer Id can't be empty")
	private Integer customerId;
	
}
