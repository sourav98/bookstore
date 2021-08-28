package com.cg.bookstore.dto;

import java.time.LocalDate;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto
{
	// Fields

	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookId;

	@NotEmpty(message = "Title is missing")
	@Size(min = 1, max = 100)
	private String title;

	@NotEmpty(message = "Author name is missing")
	@Size(min = 1, max = 100)
	private String author;

	private int category;

	@NotEmpty(message = "Please provide some description")
	@Size(min = 1, max = 200)
	private String description;

	@NotEmpty(message = "ISBN number is missing")
	@Size(min = 1, max = 100)
	private String isbn;

	@NotEmpty(message = "Please mention price")
	@Size(min = 1, max = 100)
	private String price;

	private LocalDate publishDate;

	private LocalDate lastUpdatedOn;

	private int stock;
	
	private String imgUrl;
}
