package com.cg.bookstore.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.dto.BookDto;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.exception.BookNotFoundException;
import com.cg.bookstore.exception.CategoryExistenceException;
import com.cg.bookstore.exception.DuplicateBookException;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.ICategoryRepository;

@Service
public class BookServiceImpl implements IBookService
{

	final static Logger LOGGER = LogManager.getLogger(BookServiceImpl.class);
	@Autowired
	IBookRepository bookRepo;

	@Autowired
	ICategoryRepository catRepo;

	// Creating a record
	@Override
	public Book createBook(BookDto b)
	{
		LOGGER.info("Adding a new Book using Book Service Implementation");
		List<Book> book = bookRepo.getBookByTitle(b.getTitle());
		Optional<Category> category = catRepo.findById(b.getCategory());

		if (!(category.isPresent()))
		{
			LOGGER.error("No category found");
			throw new CategoryExistenceException("No Category found with Category Id :" + b.getCategory());

		}

		if (!book.isEmpty())
		{
			LOGGER.error("Book already exists");
			throw new DuplicateBookException("Book already exists with Title :" + b.getTitle());
		}

		Book bookData = new Book();
		bookData.setBookId(b.getBookId());
		bookData.setTitle(b.getTitle());
		bookData.setAuthor(b.getAuthor());
		bookData.setCategory(category.get());
		bookData.setDescription(b.getDescription());
		bookData.setIsbn(b.getIsbn());
		bookData.setPrice(b.getPrice());
		bookData.setPublishDate(b.getPublishDate());
		bookData.setLastUpdatedOn(b.getLastUpdatedOn());
		bookData.setStock(b.getStock());
		bookData.setImgUrl(b.getImgUrl());
		return bookRepo.save(bookData);
	}

	// Getting all Books
	@Override
	public List<Book> listAllBooks()
	{
		LOGGER.info("Fetching all Books using Book Service Implementation");
		List<Book> books = bookRepo.findAll();

		if (books.isEmpty())
		{
			LOGGER.error("No books found");
			throw new BookNotFoundException("No Books Found");
		}
		return books;
	}

	// Deleting Book based on id
	@Override
	public Book deleteBook(int bookId)
	{
		// int bookId = b.getBookId();
		LOGGER.info("Deleting a Book using Book Service Implementation");
		Optional<Book> book = bookRepo.findById(bookId);

		if (!book.isPresent())
		{
			LOGGER.error("No book found");
			throw new BookNotFoundException("No Books Found with id " + bookId);
		}
		bookRepo.deleteById(bookId);
		return book.get();

	}

	// Editing Book Details
	@Override
	public Book editBook(Book b)
	{
		LOGGER.info("Editing a Book using Book Service Implementation");
		// Finding Book
		Optional<Book> book = bookRepo.findById(b.getBookId());

		// Updating Book details
		if (!book.isPresent())
		{
			LOGGER.error("No book found");
			throw new BookNotFoundException("No Books Found with id " + b.getBookId());
		} else
		{
			Book entity = book.get();
			entity.setBookId(b.getBookId());
			entity.setTitle(b.getTitle());
			entity.setAuthor(b.getAuthor());
			entity.setCategory(b.getCategory());
			entity.setDescription(b.getDescription());
			entity.setIsbn(b.getIsbn());
			entity.setPrice(b.getPrice());
			entity.setPublishDate(b.getPublishDate());
			entity.setLastUpdatedOn(b.getLastUpdatedOn());
			bookRepo.deleteById(b.getBookId());
			bookRepo.save(entity);
		}

		return b;
	}

	@Override
	public Book viewBook(int bookId)
	{
		LOGGER.info("Fetching a particular book via book id using Book Service Implementation");
		Optional<Book> book = bookRepo.findById(bookId);

		if (book.isEmpty())
		{
			LOGGER.error("No book found");
			throw new BookNotFoundException("No Books Found with id " + bookId);
		}
		return book.get();
	}

	@Override
	public List<Book> listBooksByCategory(String cat)
	{
		LOGGER.info("Fetching all Books of a category using Book Service Implementation");
		// Calling a Custom method from Book Repository
		return bookRepo.listBooksByCategory(cat);
		// return null ;
	}
	
	
	// Custom Method For getting a list of category specific books, throws BookNotFoundException
	@Override
	public List<Book> listBooksByCategoryId(int categoryId)
	{
		LOGGER.info("Fetching all Books of a categoryId using Book Service Implementation");
		List<Book> book = bookRepo.listBooksByCategoryId(categoryId) ;
		if(book.isEmpty())
		{
			LOGGER.error("No book found");
			throw new BookNotFoundException("No books found with given category Id : "+categoryId) ;
		}
		return book ;
	}

	@Override
	public List<Book> listBestSellingBooks()
	{
		LOGGER.info("Fetching best selling Books using Book Service Implementation");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int editBookTitle(int bookId, String bookTitle)
	{
		LOGGER.info("Updating Book title of a particular book using Book Service Implementation");
		Optional<Book> book = bookRepo.findById(bookId);

		if (book.isEmpty())
		{
			LOGGER.error("No book found");
			throw new BookNotFoundException("No Books Found with id " + bookId);
		}
		bookRepo.editBookTitle(bookId, bookTitle);
		return 1 ;
	}

	@Override
	public void editBookPrice(int bookId, String bookPrice)
	{
		LOGGER.info("Updating price of a particular Book using Book Service Implementation");

		Optional<Book> book = bookRepo.findById(bookId);

		if (book.isEmpty())
		{
			LOGGER.error("No book found");
			throw new BookNotFoundException("No Books Found with id " + bookId);
		}
		bookRepo.editBookPrice(bookId, bookPrice);
	}

	@Override
	public void editBookDescription(int bookId, String bookDesc)
	{
		LOGGER.info("Updating description of a particular Book using Book Service Implementation");
		Optional<Book> book = bookRepo.findById(bookId);

		if (book.isEmpty())
		{
			LOGGER.error("No Book Found");
			throw new BookNotFoundException("No Books Found with id " + bookId);
		}
		bookRepo.editBookDescription(bookId, bookDesc);
	}

	@Override
	public void editBookAuthor(int bookId, String bookAuthor)
	{
		LOGGER.info("Updating author of a particular Book using Book Service Implementation");
		Optional<Book> book = bookRepo.findById(bookId);

		if (book.isEmpty())
		{
			LOGGER.error("No book found");
			throw new BookNotFoundException("No Books Found with id " + bookId);
		}
		bookRepo.editBookAuthor(bookId, bookAuthor);
	}

	// List all books by author
	@Override
	public List<Book> getBooksByAuthor(String author)
	{
		LOGGER.info("Fetching Books by an author using Book Service Implementation");
		List<Book> books = bookRepo.getBooksByAuthor(author);

		if (books.isEmpty())
		{
			LOGGER.error("No books found");
			throw new BookNotFoundException("No Books Found by author : " + author);
		}
		return books;
	}

	@Override
	public List<Book> getBookByTitle(String title)
	{
		LOGGER.info("Fetching a particular Book by title using Book Service Implementation");
		return bookRepo.getBookByTitle(title);
	}

	
}
