package com.cg.bookstore.dto;



import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

// Using this dto in order to add order accepting the book and order ids and perform operations based on that
public class OrderDetailsDto {

	// Book Entity
	private Integer bookId;

	// Book Order
	
	private Integer orderId;

	@NotNull(message="Provide the quantity for your order")
	@Min(value=1, message="Quantity has to be more than 0")
	private Integer quantity;
	
	
	
}
