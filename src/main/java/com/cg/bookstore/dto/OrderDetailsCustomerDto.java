package com.cg.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsCustomerDto {
	
	int bookId;
	String title;
	String price;
	Integer quantity;
	String fullName;
	int customerId;
	double orderTotal; 
	String deliveryStatus;
	
}
