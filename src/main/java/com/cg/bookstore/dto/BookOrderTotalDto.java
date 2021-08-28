package com.cg.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BookOrderTotalDto {
	
	
	private Double orderTotal;
	private String fullname;
	

}
