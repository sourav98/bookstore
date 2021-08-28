package com.cg.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsBookDto {

	private String title;
	private String price;
	private Integer quantity;

	
	
}
