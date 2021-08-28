package com.cg.bookstore.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookOrderUpdateDto {
	
private double orderTotal;
	
	@NotEmpty
	private String status;
	
	@NotEmpty
	private Integer addressId;
	
	@NotEmpty
	private String paymentMethod;
	
	@NotEmpty
	@Size(min = 4, max = 30 ,message ="Name should be between 4 and 30 characters")
	private String recipientName;
	
	private String recipientPhone;


}
