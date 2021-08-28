package com.cg.bookstore.entities;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Entity
@Data
public class Book
{
	// Fields
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "book_id")
	private int bookId;

	@Column(name = "title")
	@NotEmpty(message = "Title is missing")
	@Size(min = 1, max = 100)
	private String title;

	@Column(name = "author")
	@NotEmpty(message = "Author name is missing")
	@Size(min = 1, max = 100)
	private String author;

	@ManyToOne(cascade = {CascadeType.PERSIST , CascadeType.REFRESH})
	@JoinColumn(name = "category_id_fk")
	private Category category;

	@Column(name = "description")
	@NotEmpty(message = "Please provide some description")
	@Size(min = 1, max = 200)
	private String description;

	@Column(name = "isbn")
	@NotEmpty(message = "ISBN number is missing")
	@Size(min = 1, max = 100)
	private String isbn;

	@Column(name = "price")
	@NotEmpty(message = "Please mention price")
	@Size(min = 1, max = 100)
	private String price;

	@Column(name = "publish_date")
	private LocalDate publishDate;

	@Column(name = "last_updated_on")
	private LocalDate lastUpdatedOn;

	@Column(name = "stock")
	private int stock;
	
	private String imgUrl;


	
}
