package com.cg.bookstore.service;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.bookstore.entities.Category;


@SpringBootTest
public class CategoryServiceJUnitTest {

	@Autowired
	ICategoryService catService;
	//get all categories
	@Test
	void testViewAllCategories() {
		List<Category> categories = catService.viewAllCategories();
		assertEquals(11,categories.size());
	}
	//add category
	@Test
	void testAddCategory(){
		Category category = new Category();
		category.setCategoryId(111);
		category.setCategoryName("Book");
		
		Category persistedCat = catService.addCategory(category);
		assertEquals(111,persistedCat.getCategoryId());
		assertEquals("Book",persistedCat.getCategoryName());
	}
	//get category by id
	@Test
	void testFindCategoryById() {
		Category category = catService.getById(100);
		assertEquals("Humor",category.getCategoryName());
	}
	//get category by name
	@Test
	void testFindCategoryByName() {
		Category category = catService.getByName("Humor");
		assertEquals(100,category.getCategoryId());
	}
	//delete category by name
	@Test
	void testRemoveCategoryByName() {
		Category category = new Category(115,"Thriller");
		catService.addCategory(category);
		catService.delCategoryByName("Thriller");
		assertEquals(115,category.getCategoryId());
		assertEquals("Thriller",category.getCategoryName());
	}
	//delete category by id
	@Test
	void testRemoveCategory() {
		Category category = new Category(113,"Vocabulary");
		catService.addCategory(category);
		Category persistedCat = catService.removeCategory(113);
		assertEquals(113,persistedCat.getCategoryId());
		assertEquals("Vocabulary",persistedCat.getCategoryName());
	}
	//update category
	@Test
	void testUpdateCategory() {
		Category category = new Category(114,"Dictionary");
		catService.addCategory(category);
		Category updateCat = new Category(114,"Dictionaries");
		Category updatedCategory=catService.editCategory(updateCat);
		assertEquals(114,updatedCategory.getCategoryId());
		assertEquals("Dictionaries",updatedCategory.getCategoryName());
	}
	
}
