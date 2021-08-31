package com.cg.bookstore.dto;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReviewDto {

	@GeneratedValue
	private int reviewId;
	private int bookId;
	private int customerId;
	String headLine;
	String comment;
	double rating;
	@DateTimeFormat(pattern = "MM-DD-YY")
	private LocalDate reviewOn = LocalDate.now();
}
