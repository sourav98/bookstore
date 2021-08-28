package com.cg.bookstore.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.bookstore.entities.Book;

public interface IBookRepository extends JpaRepository<Book, Integer>
{

	@Query("SELECT o FROM Book o where o.category = (select categoryId from Category b where b.categoryName = :category)")
	public List<Book> listBooksByCategory(@Param("category") String category);

	@Query("select o from Book o where o.category = (select categoryId from Category b where b.categoryId =:categoryId)")
	public List<Book> listBooksByCategoryId(@Param("categoryId") int categoryId) ;
	
	// public List<Book> listBestSellingBooks();

	@Transactional
	@Modifying
	@Query("update Book b set b.title=:bookTitle where b.bookId=:bookId")
	public int editBookTitle(@Param("bookId") int bookId, @Param("bookTitle") String bookTitle);

	@Transactional
	@Modifying
	@Query("update Book b set b.price=:bookPrice where b.bookId=:bookId")
	public void editBookPrice(@Param("bookId") int bookId, @Param("bookPrice") String bookPrice);

	@Transactional
	@Modifying
	@Query("update Book b set b.description=:bookDescription where b.bookId=:bookId")
	public void editBookDescription(@Param("bookId") int bookId, @Param("bookDescription") String bookDescription);

	@Transactional
	@Modifying
	@Query("update Book b set b.author=:bookAuthor where b.bookId=:bookId")
	public void editBookAuthor(@Param("bookId") int bookId, @Param("bookAuthor") String bookAuthor);

	@Query("select e from Book e where e.author =:author")
	public List<Book> getBooksByAuthor(@Param("author") String author);

	@Query("select e from Book e where e.title =:title")
	public List<Book> getBookByTitle(@Param("title") String title);
	
	

}
