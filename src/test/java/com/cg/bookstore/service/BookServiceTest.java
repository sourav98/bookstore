package com.cg.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.bookstore.dto.BookDto;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Category;

import java.util.List;

@SpringBootTest
public class BookServiceTest
{
	@Autowired
	IBookService bookSer;

	// Testing weather create method is working fine
	@Test
	void testCreateBook()
	{
		Category c = new Category(102, "category");
		Book book = new Book(102, "Title", "Author", c, "description", "isbn", "1000", LocalDate.of(2021, 8, 8),
				LocalDate.of(2021, 8, 8), 10);

		BookDto dto = new BookDto();
		dto.setBookId(102);
		dto.setTitle("Title");
		dto.setAuthor("Author");
		dto.setCategory(c.getCategoryId());
		dto.setDescription("description");
		dto.setIsbn("isbn");
		dto.setPrice("1000");
		dto.setPublishDate(LocalDate.now());
		dto.setLastUpdatedOn(LocalDate.now());

		Book persistedBook = bookSer.createBook(dto);

		assertEquals(102, persistedBook.getBookId());
		assertEquals("Title", persistedBook.getTitle());
		assertEquals("Author", persistedBook.getAuthor());
		assertEquals(102, persistedBook.getCategory().getCategoryId());
		assertEquals("category", persistedBook.getCategory().getCategoryName());
		assertEquals("description", persistedBook.getDescription());
		assertEquals("isbn", persistedBook.getIsbn());
		assertEquals("price", persistedBook.getPrice());
		assertEquals(LocalDate.of(2021, 8, 8), persistedBook.getPublishDate());
		assertEquals(LocalDate.of(2021, 8, 8), persistedBook.getLastUpdatedOn());

	}

	// Test method for checking find all operation
	@Test
	void testListAllBooks()
	{
		Category c = new Category(100, "category");
		Book book1 = new Book(101, "Title", "Author", c, "description", "isbn", "1000", LocalDate.of(2021, 8, 8),
				LocalDate.of(2021, 8, 8), 10);

		Book book2 = new Book(102, "Title", "Author", c, "description", "isbn", "1000", LocalDate.of(2021, 8, 8),
				LocalDate.of(2021, 8, 8), 10);

		BookDto dto = new BookDto();
		dto.setBookId(102);
		dto.setTitle("Title");
		dto.setAuthor("Author");
		dto.setCategory(c.getCategoryId());
		dto.setDescription("description");
		dto.setIsbn("isbn");
		dto.setPrice("1000");
		dto.setPublishDate(LocalDate.now());
		dto.setLastUpdatedOn(LocalDate.now());

		BookDto dto2 = new BookDto();
		dto2.setBookId(102);
		dto2.setTitle("Title");
		dto2.setAuthor("Author");
		dto2.setCategory(c.getCategoryId());
		dto2.setDescription("description");
		dto2.setIsbn("isbn");
		dto2.setPrice("1000");
		dto2.setPublishDate(LocalDate.now());
		dto2.setLastUpdatedOn(LocalDate.now());

		bookSer.createBook(dto);
		bookSer.createBook(dto2);

		List<Book> books = bookSer.listAllBooks();
		assertEquals(7, books.size());
	}

	// Test method for deletion operation
	@Test
	void testDeleteBook()
	{
		Category c = new Category(100, "category");
		Book book = new Book(101, "Title", "Author", c, "description", "isbn", "1000", LocalDate.of(2021, 8, 8),
				LocalDate.of(2021, 8, 8), 10);

		BookDto dto = new BookDto();
		dto.setBookId(102);
		dto.setTitle("Title");
		dto.setAuthor("Author");
		dto.setCategory(c.getCategoryId());
		dto.setDescription("description");
		dto.setIsbn("isbn");
		dto.setPrice("1000");
		dto.setPublishDate(LocalDate.now());
		dto.setLastUpdatedOn(LocalDate.now());

		bookSer.createBook(dto);
		Book persistedBook = bookSer.deleteBook(book.getBookId());

		assertEquals(101, persistedBook.getBookId());
		assertEquals("Title", persistedBook.getTitle());
		assertEquals("Author", persistedBook.getAuthor());
		assertEquals(100, persistedBook.getCategory().getCategoryId());
		assertEquals("description", persistedBook.getDescription());
		assertEquals("isbn", persistedBook.getIsbn());
		assertEquals("1000", persistedBook.getPrice());
		assertEquals(LocalDate.of(2021, 8, 8), persistedBook.getPublishDate());
		assertEquals(LocalDate.of(2021, 8, 8), persistedBook.getLastUpdatedOn());

	}

	// Test method for Update operation
	@Test
	void testEditBook()
	{

		Category c = new Category(100, "category");
		Book bookOriginal = new Book(101, "Title", "Author", c, "description", "isbn", "500", LocalDate.of(2021, 8, 8),
				LocalDate.of(2021, 8, 8), 10);

		BookDto dto = new BookDto();
		dto.setBookId(102);
		dto.setTitle("Title");
		dto.setAuthor("Author");
		dto.setCategory(c.getCategoryId());
		dto.setDescription("description");
		dto.setIsbn("isbn");
		dto.setPrice("1000");
		dto.setPublishDate(LocalDate.now());
		dto.setLastUpdatedOn(LocalDate.now());

		Book originalBook = bookSer.createBook(dto);

		Book bookForEdit = new Book(101, "Harry Potter", "Author", c, "description", "isbn", "1000",
				LocalDate.of(2021, 8, 9), LocalDate.of(2021, 8, 9), 10);

		BookDto dto2 = new BookDto();
		dto2.setBookId(bookForEdit.getBookId());
		dto2.setTitle(bookForEdit.getTitle());
		dto2.setAuthor(bookForEdit.getAuthor());
		dto2.setCategory(c.getCategoryId());
		dto2.setDescription(bookForEdit.getDescription());
		dto2.setIsbn(bookForEdit.getDescription());
		dto2.setPrice(bookForEdit.getPrice());
		dto2.setPublishDate(bookForEdit.getPublishDate());
		dto2.setLastUpdatedOn(bookForEdit.getLastUpdatedOn());

		Book bookEdited = bookSer.editBook(bookForEdit);

		assertEquals(101, bookEdited.getBookId());
		assertEquals("Harry Potter", bookEdited.getTitle());
		assertEquals("Author", bookEdited.getAuthor());
		assertEquals(100, bookEdited.getCategory().getCategoryId());
		assertEquals("description", bookEdited.getDescription());
		assertEquals("isbn", bookEdited.getIsbn());
		assertEquals("price", bookEdited.getPrice());
		assertEquals(LocalDate.of(2021, 8, 9), bookEdited.getPublishDate());
		assertEquals(LocalDate.of(2021, 8, 9), bookEdited.getLastUpdatedOn());

	}

	// Test method for viewing operation
	@Test
	void testViewBook()
	{
		Category c = new Category(100, "category");
		Book book = new Book(101, "Harry Potter", "Author", c, "description", "isbn", "price", LocalDate.of(2021, 8, 9),
				LocalDate.of(2021, 8, 9), 10);

		// bookSer.createBook(book);
		Book bookFetched = bookSer.viewBook(book.getBookId());
		assertEquals(101, bookFetched.getBookId());
		assertEquals("Harry Potter", bookFetched.getTitle());
		assertEquals("Author", bookFetched.getAuthor());
		// assertEquals(c,persistedBook.getCategory()) ;
		assertEquals("description", bookFetched.getDescription());
		assertEquals("isbn", bookFetched.getIsbn());
		assertEquals("price", bookFetched.getPrice());
		assertEquals(LocalDate.of(2021, 8, 9), bookFetched.getPublishDate());
		assertEquals(LocalDate.of(2021, 8, 9), bookFetched.getLastUpdatedOn());
	}

	// Test method for listing the books by a given category
	@Test
	void testListBooksByCategory()
	{
		Category c = new Category(101, "action");
		Book book = new Book(101, "Harry Potter", "Author", c, "description", "isbn", "price", LocalDate.of(2021, 8, 9),
				LocalDate.of(2021, 8, 9), 10);
		// bookSer.createBook(book);
		List<Book> books = bookSer.listBooksByCategory("action");

		assertEquals(false, books.isEmpty());
	}

}