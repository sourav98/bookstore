package com.cg.bookstore.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.bookstore.entities.Review;
import com.cg.bookstore.service.IReviewService;

@RestController
public class ReviewController
{
	final static Logger LOGGER = LogManager.getLogger(ReviewController.class);
	@Autowired
	IReviewService revServ;

	@GetMapping("/review/all")
	public List<Review> listAllReviews()
	{
		LOGGER.info("Fetching all reviews using get mapping via Review Controller");
		return revServ.listAllReviews();
	}

	@PostMapping("/review")
	public Review addReview(@Valid @RequestBody Review review)
	{
		LOGGER.info("Adding review using post mapping via Review Controller");
		return revServ.addReview(review);
	}

	@DeleteMapping("/review/{id}")
	public void deleteReview(@PathVariable("id") int id)
	{
		LOGGER.info("Deleting particular review using delete mapping via Review Controller");
		revServ.deleteReview(id);
	}

	@PutMapping("/review")
	public Review updateReview(@Valid @RequestBody Review rev)
	{
		LOGGER.info("Updating a review using put mapping via Review Controller");
		return revServ.updateReview(rev);
	}

	@PatchMapping("/review/{id}")
	public Review updateHeadLine(@PathVariable("id") int id, @RequestBody String headLine)
	{
		LOGGER.info("Updating review headline of a review using patch mapping via Review Controller");
		return revServ.updateHeadLine(id, headLine);
	}

	@PatchMapping("/review/comment/{id}")
	public Review updateComment(@PathVariable("id") int id, @RequestBody String comment)
	{
		LOGGER.info("Updating review comment of a review using patch mapping via Review Controller");
		return revServ.updateComment(id, comment);
	}

	@PatchMapping("/review/rating/{id}")
	public Review updateComment(@PathVariable("id") int id, @RequestBody double rating)
	{
		LOGGER.info("Updating review rating of a review using patch mapping via Review Controller");
		return revServ.updateRating(id, rating);
	}

	@PatchMapping("/review/reviewOn/{id}")
	public Review updateReviewon(@PathVariable("id") int id, @RequestBody String reviewon)
	{
		LOGGER.info("Updating review date of a review using patch mapping via Review Controller");
		return revServ.updateReviewon(id, reviewon);
	}

	@GetMapping("/review/{id}")
	public Review viewReviewById(@PathVariable("id") int id)
	{
		LOGGER.info("Fetching a particular review using get mapping via Review Controller");
		return revServ.viewReviewById(id);
	}

}
