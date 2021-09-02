package com.cg.bookstore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cg.bookstore.entities.Review;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Integer>
{
	@Query(value = "select * from review;", nativeQuery = true)
	List<Review> getAllReviews();

	/*
	 * public List<Review> listAllReviewsByBook(Book book); public List<Review>
	 * listAllReviewsByCustomer(Customer c); public List<Book>
	 * listMostFavoredBooks(); public List<Review> listAllReviewByCustomer(customer
	 * customer);
	 */
	
	@Query(value ="select * from review r where customer_id_fk=(:customerId);", nativeQuery=true) 
	List<Review> viewReviewByCustomerId(@Param("customerId") int customerId );

	@Query(value ="select * from review r where book_id_fk=(:bookId);", nativeQuery=true) 
	List<Review> viewReviewByBookId(@Param("bookId") int bookId );


}
