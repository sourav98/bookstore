package com.cg.bookstore.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.Category;
import com.cg.bookstore.service.ICategoryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController


public class CategoryController {
	
	final static Logger LOGGER = LogManager.getLogger(CategoryController.class);
	@Autowired
	ICategoryService catServ;

	//add category
	@PostMapping("/category")
	public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category)  {
		LOGGER.info("Adding category using post mapping via Category Controller");
		if(category.getCategoryId()!=0){
			Category c = catServ.addCategory(category);
			return new ResponseEntity<>(c, HttpStatus.ACCEPTED);
		}
		else{
			LOGGER.error("BAD Request");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

	//view all
	@GetMapping("/category/all")
	public ResponseEntity<List<Category>> viewAllCategories(){
		LOGGER.info("Fetching all categories using get mapping via Category Controller");
			return new ResponseEntity<>(catServ.viewAllCategories(), HttpStatus.OK);
	}

	//get category by id
	@GetMapping("/category/id/{id}")
	public ResponseEntity<Category> getById(@PathVariable("id") int catId) {
		LOGGER.info("Getting particular category by id using get mapping via Category Controller");
		Optional<Category> opt = Optional.ofNullable(catServ.getById(catId));
		
		if(opt.isPresent()){ 
			//Category category = catServ.getById(catId);
			return new ResponseEntity<>(opt.get(), HttpStatus.OK);
		}else {
			LOGGER.error("NOT Found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//get category by name
	@GetMapping("/category/name/{name}")
	public ResponseEntity<Category> getByName(@PathVariable("name") String catName) {
		LOGGER.info("Fetching a particular category by category name using get mapping via Category Controller");
		Optional<Category> opt = Optional.ofNullable(catServ.getByName(catName));
		
		if(opt.isPresent()) { 
			//Category category = catServ.getByName(catName);
			return new ResponseEntity<>(opt.get(), HttpStatus.OK);
		}
		else {
			LOGGER.error("No category found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	//delete category by id
	@DeleteMapping("/category/delete/{id}")
	public ResponseEntity<Category> removeCategory(@PathVariable("id") int categoryId) {
		LOGGER.info("Deleting a paricular category by id using delete mapping via Category Controller");
		Optional<Category> opt = Optional.ofNullable(catServ.getById(categoryId));
		if(opt.isPresent()){
			Category category = catServ.removeCategory(categoryId);
			return new ResponseEntity<>(category, HttpStatus.OK);
		}else {
			LOGGER.error("No category found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//delete category by name
	@DeleteMapping("/category/delete/name/{name}")
	public ResponseEntity<?> delCategoryName(@PathVariable("name") String categoryName) {
		LOGGER.info("Deleting a particular category by category name using delete mapping via Category Controller");
		catServ.delCategoryByName(categoryName);
		return new ResponseEntity<>( HttpStatus.ACCEPTED);
			//System.out.print(categoryName+ "is deleted");
	}

	//update category an existing category
	@PutMapping("/category/{id}")
	public ResponseEntity<Category> editCategory(@RequestBody Category category) {
		LOGGER.info("Updating a particular category using put mapping via Category Controller");
			return new ResponseEntity<>(catServ.editCategory(category), HttpStatus.OK);
	}
}


