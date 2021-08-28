package com.cg.bookstore.service;

//imports
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.cg.bookstore.dto.BookDto;
import com.cg.bookstore.entities.Book;

public interface IBookService
{
	// Method Specifications
	public Book createBook(BookDto b);

	public List<Book> listAllBooks();

	public Book deleteBook(int bookId);

	public Book editBook(Book b);

	public Book viewBook(int bookId);

	

	// Custom Methods

	public int editBookTitle(int bookId, String bookTitle);

	public void editBookPrice(int bookId, String bookPrice);

	public void editBookDescription(int bookId, String bookDesc);

	public void editBookAuthor(int bookId, String bookAuthor);
	
	public List<Book> getBooksByAuthor(String author) ;
	
	public List<Book> getBookByTitle(String title) ;

	// Methods Using Relationships
	
	public List<Book> listBooksByCategory(String cat);

	public List<Book> listBestSellingBooks();
	
	public List<Book> listBooksByCategoryId(int categoryId) ;

}








