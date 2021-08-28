package com.cg.bookstore.service;

import java.time.LocalDate;
import java.util.List;
import com.cg.bookstore.entities.Review;


public interface IReviewService {
	
		public List<Review> listAllReviews();
		public Review addReview(Review review);
		public Review deleteReview(int reviewId);
		public Review updateReview(Review review);
		public Review viewReviewById( int reviewId);
		public Review updateHeadLine(int reviewId,String headLine);
		public Review updateComment(int reviewId,String comment);
		public Review updateRating(int reviewId,double rating);
		public Review updateReviewon(int reviewId,String date);
		/*
		public List<Review> listAllReviewsByBook(Book book); 
		public List<Review> listAllReviewsByCustomer(Customer c);
		public List<Book> listMostFavoredBooks();
		public List<Review> listAllReviewByCustomer(customer customer);
		 */
	

}
