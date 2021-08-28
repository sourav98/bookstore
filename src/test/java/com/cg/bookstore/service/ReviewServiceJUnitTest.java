package com.cg.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.bookstore.entities.Review;

@SpringBootTest
public class ReviewServiceJUnitTest
{
	@Autowired
	IReviewService revService;

	@Test
	void testListAllReviews()
	{
		List<Review> reviews = revService.listAllReviews();
		assertEquals(14, reviews.size());
	}

	@Test
	void testViewReviewById()
	{
		Review review = revService.viewReviewById(5);
		assertEquals("oogtk", review.getHeadLine());
		assertEquals("worst book ever", review.getComment());
		assertEquals(3, review.getRating());
	}

	@Test
	void testdeleteReview()
	{
		Review review = new Review(85, "hjdgchjdsgf", "fdsfsdfhsdkjfghjzgfhfdgha", 6.5);
		Review persistedrev = revService.deleteReview(85);
		assertEquals(85, persistedrev.getReviewId());
		assertEquals("hjdgchjdsgf", persistedrev.getHeadLine());
		assertEquals("fdsfsdfhsdkjfghjzgfhfdgha", persistedrev.getComment());
		assertEquals(6.5, persistedrev.getRating());
	}

	@Test
	void testAddReview()
	{
		Review review = new Review();
		review.setReviewId(85);
		review.setHeadLine("hjdgchjdsgf");
		review.setComment("fdsfsdfhsdkjfghjzgfhfdgha");
		review.setRating(6.5);
		assertEquals(review, revService.addReview(review));
	}

	@Test
	void testUpdateReview()
	{
		Review r = new Review(4, "bad book", "bad book", 6);
		Review rup = new Review(4, "chgscg", "chuhdh", 6.5);
		Review up = revService.updateReview(rup);
		assertEquals(4, rup.getReviewId());
		assertEquals("chgscg", rup.getHeadLine());
		assertEquals("chuhdh", rup.getComment());
		assertEquals(6.5, rup.getRating());

	}

	@Test
	void testUpdateHeadLine()
	{
		Review r = new Review(4, "fbdfgfbdbg", "chuhdh", 6.5);
		Review update = revService.updateHeadLine(4, "vdhgfyuyuzdhg");
		assertEquals("vdhgfyuyuzdhg", update.getHeadLine());
	}

	@Test
	void testUpdateComment()
	{
		Review r = new Review(4, "fbdfgfbdbg", "chuhdh", 6.5);
		Review update = revService.updateComment(4, "vdhgfyuyuzdhg");
		assertEquals("vdhgfyuyuzdhg", update.getComment());

	}

	@Test
	void testUpdateRating()
	{
		Review r = new Review(4, "fbdfgfbdbg", "chuhdh", 6.5);
		Review update = revService.updateRating(4, 9.6);
		assertEquals("vdhgfyuyuzdhg", update.getRating());

	}
}
