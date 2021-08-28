package com.cg.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cg.bookstore.entities.Category;

@Component
public interface ICategoryService
{

	public List<Category> viewAllCategories(); // get all

	public Category getById(int catId); // get by id

	public Category getByName(String categoryName); // get by name

	public Category addCategory(Category category); // post

	public Category removeCategory(int CategoryId); // delete

	public void delCategoryByName(String categoryName);// delete

	public Category editCategory(Category category);// patch

}