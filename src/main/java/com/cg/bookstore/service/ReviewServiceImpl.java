package com.cg.bookstore.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.dto.OrderDetailsCustomerDto;
import com.cg.bookstore.dto.ReviewDto;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Review;
import com.cg.bookstore.exception.ReviewNotFoundException;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.ICustomerRepository;
import com.cg.bookstore.repository.IReviewRepository;

@Service
public class ReviewServiceImpl implements IReviewService
{
	
	final static Logger LOGGER = LogManager.getLogger(ReviewServiceImpl.class);
	@Autowired
	IReviewRepository reviewRepository;
	
	@Autowired
	IBookRepository bookRepo;
	@Autowired
	ICustomerRepository customerRepo;

	@Override
	/*** to get list of all reviews ***/

	public List<Review> listAllReviews()
	{
		LOGGER.info("Fetching all reviews using Review Service Implementation");
		List<Review> allReviews = reviewRepository.findAll();
		if (allReviews.isEmpty())
		{
			LOGGER.error("No reviews");
			throw new ReviewNotFoundException("No reviews added yet"); // exception handled in
																			// ReviewExceptionHandler--->
																			// handleException
		}
		return allReviews;
	}

	/*** to add review ***/

	@Override
	public Review addReview(ReviewDto reviewDto)
	{
		LOGGER.info("Adding new review using Review Service Implementation");
		Optional<Book> book = bookRepo.findById(reviewDto.getBookId());
		Optional<Review> opt = reviewRepository.findById(reviewDto.getReviewId());
		Optional<Customer> customer = customerRepo.findById(reviewDto.getCustomerId());
		Review review = new Review();
		
		if (opt.isPresent())
		{
			LOGGER.error("Review present");
			throw new ReviewNotFoundException("review already present with review id " + review.getReviewId()); // exception
																												// handled
																												// in
																												// ReviewExceptionHandler--->
																												// handleException

		}
		else {
			if (book.isPresent() ) {
			review.setReviewId(reviewDto.getReviewId());
				review.setBook(book.get());
				review.setComment(reviewDto.getComment());
				review.setHeadLine(reviewDto.getHeadLine());
				review.setRating(reviewDto.getRating());
				review.setReviewOn(reviewDto.getReviewOn());	
				review.setCustomer(customer.get());
				
			}
			
		}
		return reviewRepository.save(review);
	}

	/*** to delete review by review id ***/

	public Review deleteReview(int reviewId)
	{
		LOGGER.info("Deleting a review using Review Service Implementation");
		Optional<Review> opt = reviewRepository.findById(reviewId);
		if (!opt.isPresent())
		{
			LOGGER.error("No review found");
			throw new ReviewNotFoundException("Review not found with the given id: " + reviewId); // exception handled
																									// in
																									// ReviewExceptionHandler--->
																									// handleException
		}
		reviewRepository.deleteById(reviewId);
		return opt.get();
	}

	@Override
	/*** update review by ReviewId ***/

	public Review updateReview(Review review)
	{
		LOGGER.info("Updating a review using Review Service Implementation");
		Optional<Review> opt = reviewRepository.findById(review.getReviewId());
		if (!opt.isPresent())
		{
			LOGGER.error("No review present");
			throw new ReviewNotFoundException("Review not found with the given id: " + review.getReviewId()); // exception
																												// handled
																												// in
																												// ReviewExceptionHandler--->
																												// handleException
		}
		Review rev = opt.get();
		rev.setHeadLine(review.getHeadLine());
		rev.setComment(review.getComment());
		rev.setRating(review.getRating());
		rev.setReviewOn(review.getReviewOn());
		reviewRepository.save(rev);
		return review;
	}

	/*** to view a particular review by review id ***/

	public Review viewReviewById(int reviewId)
	{
		LOGGER.info("Fetching a review by id using Review Service Implementation");
		Optional<Review> opt = reviewRepository.findById(reviewId);
		if (!opt.isPresent())
		{
			LOGGER.error("No review present");
			throw new ReviewNotFoundException("Review not found with the given id: " + reviewId); // exception handled
																									// in
																									// ReviewExceptionHandler--->
																									// handleException
		}
		return (Review) opt.get();
	}
	
	public List<Review> viewReviewByCustomerId(int customerId)
	{
		LOGGER.info("Fetching a review by id using Review Service Implementation");
		Optional<Customer> customer = customerRepo.findById(customerId);
		List<Review> opt = new ArrayList<>();
	
		
		
		if (!customer.isPresent())
		{
			LOGGER.error("No review present");
			throw new ReviewNotFoundException("Review not found with the given id: " + customerId); // exception handled
																									// in
																									// ReviewExceptionHandler--->
																									// handleException
		}
		reviewRepository.viewReviewByCustomerId(customerId).forEach(opt::add);
		return opt;
	}
	
	public List<Review> viewReviewByBookId(int bookId)
	{
		LOGGER.info("Fetching a review by id using Review Service Implementation");
		Optional<Book> book = bookRepo.findById(bookId);
		List<Review> opt = new ArrayList<>();
	
		
		
		if (!book.isPresent())
		{
			LOGGER.error("No review present");
			throw new ReviewNotFoundException("Book not found with the given id: " + bookId); // exception handled
																									// in
																									// ReviewExceptionHandler--->
																									// handleException
		}
		reviewRepository.viewReviewByBookId(bookId).forEach(opt::add);
		return opt;
	}

	/*** update headline by review id ***/
	public Review updateHeadLine(int reviewId, String headLine)
	{
		LOGGER.info("Updating headline of a review using Review Service Implementation");
		Optional<Review> opt = reviewRepository.findById(reviewId);
		if (!opt.isPresent())
		{
			LOGGER.error("No review found");
			throw new ReviewNotFoundException(
					"Review not found with given id: " + reviewId + " so headline can't be updated");
		}
		Review rev = opt.get();
		rev.setHeadLine(headLine);
		reviewRepository.save(rev);
		return opt.get();
	}

	public Review updateComment(int reviewId, String comment)
	{
		LOGGER.info("Updating review comment using Review Service Implementation");
		Optional<Review> opt = reviewRepository.findById(reviewId);
		if (!opt.isPresent())
		{
			LOGGER.error("No review found");
			throw new ReviewNotFoundException(
					"Review not found with given id: " + reviewId + " so comment can't be updated");
		}
		Review rev = opt.get();
		rev.setComment(comment);
		reviewRepository.save(rev);
		return opt.get();
	}

	@Override
	public Review updateRating(int reviewId, double rating)
	{
		LOGGER.info("Updating review rating using Review Service Implementation");
		Optional<Review> opt = reviewRepository.findById(reviewId);
		if (!opt.isPresent())
		{
			LOGGER.error("No review found");
			throw new ReviewNotFoundException(
					"Review not found with given id: " + reviewId + " so rating can't be updated");
		}
		Review rev = opt.get();
		rev.setRating(rating);
		reviewRepository.save(rev);
		return opt.get();
	}

	@Override
	public Review updateReviewon(int reviewId, String date)
	{
		LOGGER.info("Updating review date using Review Service Implementation");
		Optional<Review> opt = reviewRepository.findById(reviewId);
		if (!opt.isPresent())
		{
			LOGGER.error("No review found");
			throw new ReviewNotFoundException(
					"Review not found with given id: " + reviewId + " so date can't be updated");
		}
		LocalDate date1 = LocalDate.parse(date);
		Review rev = opt.get();
		rev.setReviewOn(date1);
		reviewRepository.save(rev);

		return opt.get();
	}
}