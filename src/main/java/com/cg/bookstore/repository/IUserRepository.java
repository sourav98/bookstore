package com.cg.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer>
{

}
