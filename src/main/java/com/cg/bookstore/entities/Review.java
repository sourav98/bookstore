package com.cg.bookstore.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Table(name = "review")
@Data
public class Review
{

	@Id
	private int reviewId;
	// private Book book;
	// private Customer customer;
	@Column(name = "head_Line")
	@NotEmpty
	@Size(min = 5, max = 40, message = "headLine should be between 5 and 40 characters")
	private String headLine;
	@Column(name = "comment")
	@NotEmpty
	@Size(min = 5, max = 40, message = "comment should be between 5 and 40 characters")
	private String comment;
	@Column(name = "rating")
	@DecimalMax("10.0")
	@DecimalMin("0.0")
	private double rating;
	@Column(name = "review_on")
	@DateTimeFormat(pattern = "MM-DD-YY")
	private LocalDate reviewOn = LocalDate.now();

	public Review()
	{

	}

	public Review(int reviewId, String headLine, String comment, double rating, LocalDate reviewOn)
	{
		super();
		this.reviewId = reviewId;
		this.headLine = headLine;
		this.comment = comment;
		this.rating = rating;
		this.reviewOn = reviewOn;
	}

	@Override
	public String toString()
	{
		return "Review [reviewId=" + reviewId + ", headLine=" + headLine + ", comment=" + comment + ", rating=" + rating
				+ ", reviewOn=" + reviewOn + "]";
	}

	public Review(int reviewId,
			@NotEmpty @Size(min = 5, max = 40, message = "headLine should be between 5 and 40 characters") String headLine,
			@NotEmpty @Size(min = 5, max = 40, message = "comment should be between 5 and 40 characters") String comment,
			@DecimalMax("10.0") @DecimalMin("0.0") double rating)
	{
		super();
		this.reviewId = reviewId;
		this.headLine = headLine;
		this.comment = comment;
		this.rating = rating;
	}

}
