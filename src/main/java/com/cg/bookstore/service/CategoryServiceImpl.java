package com.cg.bookstore.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Category;
import com.cg.bookstore.exception.CategoryExistenceException;
import com.cg.bookstore.repository.ICategoryRepository;
@Component
@Service
public class CategoryServiceImpl implements ICategoryService {
	
	final static Logger LOGGER = LogManager.getLogger(CategoryServiceImpl.class);
	@Autowired
	ICategoryRepository catRep;
	
	//add category
		@Override
		public Category addCategory(Category category) {
			
			LOGGER.info("Adding category using Category Service implementation");
			Optional<Category> opt = catRep.findById(category.getCategoryId());
			if(opt.isPresent()) {
				LOGGER.error("Category with given id already exists");
				throw new CategoryExistenceException("Category already exits with given id : "+category.getCategoryId());
			}
			return catRep.save(category);
		}
	//List all categories
	@Override
	public List<Category> viewAllCategories() {
		LOGGER.info("Fetching all Categories using Category Service implementation");
		List<Category> allCat = catRep.findAll();
		if(allCat.isEmpty()) {
			LOGGER.error("No categories exist");
			throw new CategoryExistenceException("Category List is empty!!!");
		}
		return allCat;
	}
	//get category by id
	@Override
	public Category getById(int catId) {
		LOGGER.info("Fetching a particular Category using Category Service implementation");
		Optional<Category> opt = catRep.findById(catId);
		if(!opt.isPresent()) {
			LOGGER.error("Category with given id not present");
			throw new CategoryExistenceException("Category not found with the given id:"+catId);
		}
		return opt.get();
	}
	
	@Override
	public Category getByName(String categoryName) {
		LOGGER.info("Fetching a particular Category by name using Category Service implementation");
		return catRep.getByName(categoryName);
	}
	
	
	//delete category by id
	@Override
	public Category removeCategory(int categoryId) {
		LOGGER.info("Deleting a particular Category by id using Category Service implementation");
		Optional<Category> opt = catRep.findById(categoryId);
		if(!opt.isPresent()) {
			LOGGER.error("No category present with given id");
			throw new CategoryExistenceException("Category not found with the given id:"+categoryId);
		}
		 catRep.deleteById(categoryId);
		 return opt.get();
	}
	//delete by name
	@Override
	public void delCategoryByName(String categoryName) {	
		LOGGER.info("Deleting a particular category by name using Category Services Implementation");
		catRep.deleteByName(categoryName);
	
	}
	
	
	//Update category
	@Override
	public Category editCategory(Category category) {
		LOGGER.info("Updating a Category using Category Service implementation");
		Optional<Category> opt = catRep.findById(category.getCategoryId());
		if(!opt.isPresent()) {
			LOGGER.error("No category present with given id");
			throw new CategoryExistenceException("Category not found with the given id : "+category.getCategoryId());
		}
		Category dbCat = opt.get();
		dbCat.setCategoryName(category.getCategoryName());
		catRep.save(dbCat);
		return category;
	}
}

