package com.cg.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bookstore.entities.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer>
{
	@Query("select c from Category c where c.categoryName=:cname")
	Category getByName(@Param("cname") String categoryName);

	@Transactional
	@Modifying
	@Query("delete from Category c where c.categoryName =:cname")
	void deleteByName(@Param("cname") String categoryName);

}
