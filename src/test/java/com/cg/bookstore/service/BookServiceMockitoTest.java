package com.cg.bookstore.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.bookstore.dto.BookDto;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.ICategoryRepository;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class BookServiceMockitoTest
{
	@InjectMocks
	BookServiceImpl bookSer;

	@InjectMocks
	CategoryServiceImpl catSer ;
	
	@MockBean
	IBookRepository bookRepo;

	@MockBean
	ICategoryRepository catRepo;
	
    @BeforeEach
	void init()
	{
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testMockitoCreateBook()
	{
		Category c = new Category();
		c.setCategoryId(8);
		c.setCategoryName("fiction");

		Book response = new Book();
		BookDto dto = new BookDto();

		dto.setBookId(1);
		dto.setTitle("Good Days");
		dto.setAuthor("Amit");
		dto.setCategory(8);
		dto.setDescription("Read once");
		dto.setIsbn("I20");
		dto.setPrice("1000");
		dto.setPublishDate(LocalDate.now());
		dto.setLastUpdatedOn(LocalDate.now());

		Mockito.when(catSer.addCategory(c)).thenReturn(c);
		Mockito.when(bookRepo.save(response)).thenReturn(response);
		

		assertEquals(dto.getCategory(), 8);

	}

	@Test
	void testMockitoListAllBooks()
	{
		Category c = new Category();
		c.setCategoryId(100);
		c.setCategoryName("action");

		Book b = new Book();
		b.setBookId(100);
		b.setTitle("Good Days");
		b.setAuthor("Amit");
		b.setCategory(c);
		b.setDescription("Read once");
		b.setIsbn("I20");
		b.setPrice("1000");
		b.setPublishDate(LocalDate.now());
		b.setLastUpdatedOn(LocalDate.now());

		Book b2 = new Book();
		b2.setBookId(100);
		b2.setTitle("Good Days");
		b2.setAuthor("Amit");
		b2.setCategory(c);
		b2.setDescription("Read once");
		b2.setIsbn("I20");
		b2.setPrice("1000");
		b2.setPublishDate(LocalDate.now());
		b2.setLastUpdatedOn(LocalDate.now());

		List<Book> books = new ArrayList<>();
		books.add(b);
		books.add(b2);

		Mockito.when(bookRepo.findAll()).thenReturn(books);

		assertEquals(2, books.size());
		assertEquals("Amit", books.get(0).getAuthor());

	}

	@Test
	void testMockitoDeleteBook()
	{
		Category c = new Category();
		c.setCategoryId(100);
		c.setCategoryName("action");

		Book b = new Book();
		b.setBookId(100);
		b.setTitle("Good Days");
		b.setAuthor("Amit");
		b.setCategory(c);
		b.setDescription("Read once");
		b.setIsbn("I20");
		b.setPrice("1000");
		b.setPublishDate(LocalDate.now());
		b.setLastUpdatedOn(LocalDate.now());

		Mockito.when(bookRepo.findById(100)).thenReturn(Optional.of(b));
		Book response = bookSer.deleteBook(100);

		assertEquals(b, response);

	}

	@Test
	void testEditBook()
	{
		Category c = new Category(100, "category");
		Book bookOriginal = new Book(101, "Title", "Author", c, "description", "isbn", "price",
				LocalDate.of(2021, 8, 8), LocalDate.of(2021, 8, 8), 10);

		Book bookForEdit = new Book(101, "Harry Potter", "Author", c, "description", "isbn", "price",
				LocalDate.of(2021, 8, 9), LocalDate.of(2021, 8, 9), 10);

		Mockito.when(bookRepo.findById(101)).thenReturn(Optional.of(bookOriginal));
		Mockito.when(bookRepo.save(bookOriginal)).thenReturn(bookOriginal);

		Book per = bookSer.editBook(bookForEdit);

		assertEquals(101, per.getBookId());
		assertEquals("Harry Potter", per.getTitle());

	}

	@Test
	void testViewBook()
	{
		Category c = new Category();
		c.setCategoryId(100);
		c.setCategoryName("action");

		Book b = new Book();
		b.setBookId(100);
		b.setTitle("Good Days");
		b.setAuthor("Amit");
		b.setCategory(c);
		b.setDescription("Read once");
		b.setIsbn("I20");
		b.setPrice("1000");
		b.setPublishDate(LocalDate.now());
		b.setLastUpdatedOn(LocalDate.now());

		Mockito.when(bookRepo.findById(100)).thenReturn(Optional.of(b));
		Book response = bookSer.viewBook(100);

		assertEquals(b, response);
	}

	@Test
	void testListBooksByCategory()
	{

		Category c = new Category();
		c.setCategoryId(100);
		c.setCategoryName("action");

		Book b = new Book();
		b.setBookId(100);
		b.setTitle("Good Days");
		b.setAuthor("Amit");
		b.setCategory(c);
		b.setDescription("Read once");
		b.setIsbn("I20");
		b.setPrice("1000");
		b.setPublishDate(LocalDate.now());
		b.setLastUpdatedOn(LocalDate.now());
		List<Book> books = new ArrayList<>();
		books.add(b);

		Mockito.when(bookRepo.listBooksByCategory("action")).thenReturn(books);

		assertEquals(1, books.size());
		assertEquals(100, books.get(0).getBookId());
		assertEquals("Good Days", books.get(0).getTitle());
	}

}
