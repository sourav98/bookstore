package com.cg.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.cg.bookstore.entities.Category;
import com.cg.bookstore.exception.CategoryExistenceException;
import com.cg.bookstore.repository.ICategoryRepository;

@ExtendWith(SpringExtension.class)
public class CategoryServiceMockitoTest
{
	// Injects category service and inject dependent classes or interfaces
	@InjectMocks
	CategoryServiceImpl catService;

	// injecting external services
	@MockBean
	ICategoryRepository catRep;

	// Initialization of mock objects
	@BeforeEach
	void init()
	{
		MockitoAnnotations.openMocks(this);
	}

	// testing whether all categories method is working or not
	@Test
	void testViewAllCategories()
	{
		Category c1 = new Category(1, "Sci-Fi");
		Category c2 = new Category(2, "Comic");
		Category c3 = new Category(3, "History");

		List<Category> loc = new ArrayList<>();
		loc.add(c1);
		loc.add(c2);
		loc.add(c3);

		Mockito.when(catRep.findAll()).thenReturn(loc);

		List<Category> categories = catService.viewAllCategories();

		assertEquals(3, categories.size());
	}

	// testing whether add category method is working or not
	@Test
	void testAddCategory()
	{
		Category c1 = new Category();
		c1.setCategoryId(1);
		c1.setCategoryName("Fiction");
		Mockito.when(catRep.save(c1)).thenReturn(c1);
		assertEquals(c1, catService.addCategory(c1));
	}

	// testing whether get category by id is working or not
	@Test
	void testCategoryById()
	{
		Category c1 = new Category(1, "Fiction");

		Mockito.when(catRep.findById(1)).thenReturn(Optional.of(c1));

		Category persistedCat = catService.getById(1);

		assertEquals(1, persistedCat.getCategoryId());
		assertEquals("Fiction", persistedCat.getCategoryName());

	}

	// testing whether get category by name is working or not
	@Test
	void testCategoryByName()
	{
		Category c1 = new Category(1, "Fiction");

		Mockito.when(catRep.getByName("Fiction")).thenReturn(c1);
		Category persistedCat = catService.getByName("Fiction");

		assertEquals(1, persistedCat.getCategoryId());
		assertEquals("Fiction", persistedCat.getCategoryName());
	}

	// test whether remove category method working or not
	@Test
	void testDeleteCategoryById()
	{
		Category c1 = new Category(1, "Fiction");

		Mockito.when(catRep.findById(1)).thenReturn(Optional.of(c1));
		catRep.deleteById(1);
		Category persistedCat = catService.removeCategory(1);
		assertEquals(1, persistedCat.getCategoryId());
		assertEquals("Fiction", persistedCat.getCategoryName());
	}

	// test whether remove categoty by name or not
	@Test
	void testDeleteCategoryByName()
	{
		Category c1 = new Category(1, "Fiction");
		Mockito.when(catRep.getByName("Fiction")).thenReturn(c1);
		catRep.deleteByName("Fiction");
		catService.delCategoryByName("Fiction");
		assertEquals(1, c1.getCategoryId());
		assertEquals("Fiction", c1.getCategoryName());
	}

	// test whether update category method working or not
	@Test
	void testUpdateCategory()
	{
		Category c1 = new Category(1, "Fiction");

		Mockito.when(catRep.findById(1)).thenReturn(Optional.of(c1));
		Mockito.when(catRep.save(c1)).thenReturn(c1);
		Category persistedCat = catService.editCategory(c1);
		assertEquals(1, persistedCat.getCategoryId());
		assertEquals("Fiction", persistedCat.getCategoryName());

	}

}
