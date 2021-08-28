package com.cg.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.bookstore.repository.IReviewRepository;
import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;
import com.cg.bookstore.entities.Review;

@ExtendWith(SpringExtension.class)
public class ReviewServiceMockitoTest
{

	@InjectMocks
	ReviewServiceImpl revService;

	@MockBean
	IReviewRepository revRepository;

	@BeforeEach
	void init()
	{
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testallReviews()
	{
		Review r1 = new Review(1, "goood", "very good", 9.5);
		Review r2 = new Review(2, "average", "average book", 7.5);
		Review r3 = new Review(3, "baaad", "bad book", 5);
		List<Review> listOfReviews = new ArrayList<>();
		listOfReviews.add(r1);
		listOfReviews.add(r2);
		listOfReviews.add(r3);
		Mockito.when(revRepository.findAll()).thenReturn(listOfReviews);
		List<Review> reviews = revService.listAllReviews();
		assertEquals(3, reviews.size());
	}

	@Test
	void testAddReviews()
	{
		Review r1 = new Review();
		r1.setReviewId(1);
		r1.setHeadLine("awesome");
		r1.setComment("");
		r1.setRating(10);
		Mockito.when(revRepository.save(r1)).thenReturn(r1);
		assertEquals(r1, revService.addReview(r1));
	}

//test to view reviews by Id
	@Test
	void testViewReviewsById()
	{
		Review r1 = new Review();
		r1.setReviewId(1);
		r1.setHeadLine("awesome");
		r1.setComment("");
		r1.setRating(10);
		r1.setReviewOn(LocalDate.of(2020, 8, 05));
		Mockito.when(revRepository.findById(1)).thenReturn(Optional.of(r1));
		Review response = revService.viewReviewById(1);
		assertEquals("awesome", response.getHeadLine());
		assertEquals("", response.getComment());
		assertEquals(10, response.getRating());
		assertEquals(LocalDate.of(2020, 8, 05), response.getReviewOn());

	}

	@Test
	void testDeleteReview()
	{
		Review r1 = new Review(1, "goood", "very good", 9.5, LocalDate.of(2020, 8, 05));
		Review r2 = new Review(2, "average", "average book", 7.5, LocalDate.of(2021, 8, 04));
		Mockito.when(revRepository.findById(1)).thenReturn(Optional.of(r1));
		revRepository.deleteById(1);
		Review persistedReview = revService.deleteReview(1);
		assertEquals(1, persistedReview.getReviewId());
		assertEquals("goood", persistedReview.getHeadLine());
		assertEquals("very good", persistedReview.getComment());
		assertEquals(9.5, persistedReview.getRating());
		assertEquals(LocalDate.of(2020, 8, 05), persistedReview.getReviewOn());

	}

	@Test
	void testUpdateReview()
	{
		Review r1 = new Review(1, "goood", "very good", 9.5, LocalDate.of(2020, 8, 05));
		Review r2 = new Review(2, "average", "average book", 7.5, LocalDate.of(2021, 8, 04));
		Mockito.when(revRepository.findById(1)).thenReturn(Optional.of(r1));
		revRepository.save(r1);
		Review persistedReview = revService.updateReview(r1);
		assertEquals(1, persistedReview.getReviewId());
		assertEquals("goood", persistedReview.getHeadLine());
		assertEquals("very good", persistedReview.getComment());
		assertEquals(9.5, persistedReview.getRating());
		assertEquals(LocalDate.of(2020, 8, 05), persistedReview.getReviewOn());
	}

	@Test
	void testUpdateHeadLine()
	{
		Review r1 = new Review(1, "goood", "very good", 9.5, LocalDate.of(2020, 8, 05));
		Mockito.when(revRepository.findById(1)).thenReturn(Optional.of(r1));
		revRepository.save(r1);
		Review persistedReview = revService.updateHeadLine(1, "goood");
		assertEquals("goood", persistedReview.getHeadLine());
	}

	@Test
	void testUpdateComment()
	{
		Review r1 = new Review(1, "goood", "very good", 9.5, LocalDate.of(2020, 8, 05));
		Mockito.when(revRepository.findById(1)).thenReturn(Optional.of(r1));
		revRepository.save(r1);
		Review persistedReview = revService.updateComment(1, "gooodfdfdf");
		assertEquals("gooodfdfdf", persistedReview.getComment());
	}

	@Test
	void testUpdateRating()
	{
		Review r1 = new Review(1, "goood", "very good", 9.5, LocalDate.of(2020, 8, 05));
		Mockito.when(revRepository.findById(1)).thenReturn(Optional.of(r1));
		revRepository.save(r1);
		Review persistedReview = revService.updateRating(1, 9.5);
		assertEquals(9.5, persistedReview.getRating());
	}

}
