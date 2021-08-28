package com.cg.bookstore.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.dto.BookDto;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.service.IBookService;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Api
public class BookController
{
	@Autowired
	IBookService bookSer;
	final static Logger LOGGER = LogManager.getLogger(BookController.class);

	// Adding Book
	@PostMapping("/books")
	public ResponseEntity<Book> creteBook(@Valid @RequestBody BookDto book)
	{
		LOGGER.info("Creating new book using Post Mapping via Book Controller"); 
		return new ResponseEntity<Book>(this.bookSer.createBook(book), HttpStatus.OK);
	}

	// Getting all books
	@GetMapping("/books/all")
	public ResponseEntity<List<Book>> listAllBooks()
	{
		LOGGER.info("Fetching all books using Get Mapping via Book Controller");
		return new ResponseEntity<List<Book>>(this.bookSer.listAllBooks(), HttpStatus.OK);
	}

	// Fetching A record
	@GetMapping("/books/{bookId}")
	public ResponseEntity<Book> viewBook(@PathVariable("bookId") int bookId)
	{
		LOGGER.info("Fetching particular book using Get Mapping via Book Controller");
		return new ResponseEntity<Book>(bookSer.viewBook(bookId), HttpStatus.OK);
	}

	// Fetching all books
	@GetMapping("/books/category/{category}")
	public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable("category") String category)
	{
		LOGGER.info("Fetching books of a particular category using Get Mapping via Book Controller");
		return new ResponseEntity<List<Book>>(bookSer.listBooksByCategory(category), HttpStatus.OK);
	}

	// Fetching book by given Title
	@GetMapping("/books/title/{bTitle}")
	public ResponseEntity<List<Book>> getBookByTitle(@PathVariable("bTitle") String title)
	{
		LOGGER.info("Fetching books by a particular title using Get Mapping via Book Controller");
		return new ResponseEntity<List<Book>>(bookSer.getBookByTitle(title), HttpStatus.OK);
	}

	// Fetching a book using category Id
	@GetMapping("/books/categoryId/{categoryId}")
	ResponseEntity<List<Book>> listBooksByCategoryId(@PathVariable("categoryId") int categoryId)
	{
		LOGGER.info("Fetching books by a particular category Id using Get Mapping via Book Controller");
		return new ResponseEntity<List<Book>>(bookSer.listBooksByCategoryId(categoryId), HttpStatus.OK);
	}

	// Fetching books by given author
	@GetMapping("/books/author/{aName}")
	public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable("aName") String author)
	{
		LOGGER.info("Fetching books by a particular author using Get Mapping via Book Controller");
		return new ResponseEntity<List<Book>>(bookSer.getBooksByAuthor(author), HttpStatus.OK);
	}

	// Delete the Book
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<Book> deleteBook(@PathVariable int bookId)
	{
		LOGGER.info("Deleting particular book using Get Mapping via Book Controller");
		return new ResponseEntity<Book>(this.bookSer.deleteBook(bookId), HttpStatus.OK);
	}

	// Updating an existing record using PUT request
	@PutMapping("/books")
	public ResponseEntity<Book> editBook(@RequestBody Book book)
	{
		LOGGER.info("Updating particular book using Put Mapping via Book Controller");
		return new ResponseEntity<Book>(this.bookSer.editBook(book), HttpStatus.OK);
	}

	// Editing book Title
	@PatchMapping("/books/eTitle/{bookId}")
	public ResponseEntity<?> editBookTitle(@PathVariable("bookId") int bookId, @RequestBody String bookTitle)
	{
		LOGGER.info("Updating book title of a particular book using Patch Mapping via Book Controller");
		bookSer.editBookTitle(bookId, bookTitle);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	// Editing book Price
	@PatchMapping("/books/ePrice/{bookId}")
	public ResponseEntity<?> editBookPrice(@PathVariable("bookId") int bookId, @RequestBody String bookPrice)
	{
		LOGGER.info("Updating book price of a particular book using Patch Mapping via Book Controller");
		bookSer.editBookPrice(bookId, bookPrice);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	// Editing book Description
	@PatchMapping("/books/eDesc/{bookId}")
	public ResponseEntity<?> editBookDescription(@PathVariable("bookId") int bookId, @RequestBody String bookDesc)
	{
		LOGGER.info("Updating book description of a particular book using Patch Mapping via Book Controller");
		bookSer.editBookDescription(bookId, bookDesc);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	// Editing book Author
	@PatchMapping("/books/eAuthor/{bookId}")
	public ResponseEntity<?> editBookAuthor(@PathVariable("bookId") int bookId, @RequestBody String bookAuthor)
	{
		LOGGER.info("Updating author of a particular book using Patch Mapping via Book Controller");
		bookSer.editBookAuthor(bookId, bookAuthor);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
