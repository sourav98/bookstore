package com.cg.bookstore.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "category")
public class Category
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int categoryId;
	@Column(name="category_name")
	@NotEmpty
	@Size(min=3,max=10,message="category name should be between 3 - 10 characters")
	private String categoryName;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
	// @JoinColumn(name = "book_fk")
	private List<Book> book;

	public Category(int categoryId, String categoryName)
	{
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public Category()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCategoryId()
	{
		return categoryId;
	}

	public void setCategoryId(int categoryId)
	{
		this.categoryId = categoryId;
	}

	public String getCategoryName()
	{
		return categoryName;
	}

	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Category [categoryId=");
		builder.append(categoryId);
		builder.append(", categoryName=");
		builder.append(categoryName);
		builder.append("]");
		return builder.toString();
	}
}
