package com.cg.bookstore.service;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bookstore.entities.Address;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.bookstore.dto.BookOrderDto;
import com.cg.bookstore.entities.Address;
import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.exception.BookOrderNotFoundException;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookOrderServiceTest {
	
	@Autowired
	IBookOrderService bookOrderServ;
	
	@Test
	@Order(1)
	void injectedComponentsAreNotNull() {
		assertThat(bookOrderServ).isNotNull();
	}
	
	@Test
	@Order(2)
	@Rollback(value = false)
	void testAddBookOrder() throws Exception {
		Address add1 = new Address(8219,"1/29 D Block","Raipur","India","492001"); 
		LocalDate date1 = LocalDate.parse( "2021-08-06");
		LocalDate date2 = LocalDate.now();
		Customer cust1 = new Customer(8219,"abc@gmail.com","Ashish","string","8989898989",date1);
		
		BookOrder expected = new BookOrder(10541,date2,10000,"Processing",add1,"Cash","Akash","9898989898",cust1);
		//INITIALISING DTO
	  	BookOrderDto boDto = new BookOrderDto(10000,"Processing",8219,"Cash","Akash","9898989898",8219);
	  	BookOrder response = bookOrderServ.addBookOrder(boDto);
	  	//assertEquals(expected,response);
	  	assertThat(response.getOrderId()).isGreaterThan(0);
	}
	
	@Test
	@Order(3)
	@Rollback(false)
	void testCancelBookOrder() {
		Address add1 = new Address(8219,"1/29 D Block","Raipur","India","492001"); 
		LocalDate date1 = LocalDate.parse( "2021-08-06");
		LocalDate date2 = LocalDate.now();
		Customer cust1 = new Customer(8219,"abc@gmail.com","Ashish","string","8989898989",date1);
		
		BookOrder bookOrder = new BookOrder(10362,date2,10000,"Processing",add1,"Cash","Akash","9898989898",cust1);
		bookOrderServ.cancelBookOrderById(10362);
		
		//add return statements
		//assertNull(bookOrderServ.getBookOrderById(10361));
		//assertNull(response);
		BookOrderNotFoundException ex = assertThrows(BookOrderNotFoundException.class, ()->bookOrderServ.getBookOrderById(10362));
		assertEquals(ex.getMessage(),"Book Order with id : 10362 not found !!");
		
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)
	void testListAllBookOrders() {
		List<BookOrder> bookOrderList = bookOrderServ.listAllBookOrders();
		//assertEquals(41,bookOrderList.size());
		assertThat(bookOrderList.size()).isGreaterThan(0);
	}
	
	@Test
	void testGetBookOrderById() {
		Address add1 = new Address(8219,"1/29 D Block","Raipur","India","492001"); 
		LocalDate date1 = LocalDate.parse( "2021-08-06");
		LocalDate date2 = LocalDate.now();
		Customer cust1 = new Customer(8219,"abc@gmail.com","Ashish","string","8989898989",date1);
		
		BookOrder expected = new BookOrder(10521,date2,10000,"Processing",add1,"Cash","Akash","9898989898",cust1);
		BookOrder response = bookOrderServ.getBookOrderById(10521);
		assertEquals(10521,response.getOrderId());
		
	}
	
	
	
	
	
	@Test
	void testViewBookOrderByCity() throws Exception {
		
		Address add1 = new Address(8219,"1/29 D Block","Raipur","India","492001"); 
		LocalDate date1 = LocalDate.parse( "2021-08-06");
		LocalDate date2 = LocalDate.now();
		Customer cust1 = new Customer(8219,"abc@gmail.com","Ashish","string","8989898989",date1);
		
		BookOrderDto boDto1 = new BookOrderDto(10000,"Processing",8219,"Cash","Akash","9898989898",8219);
		BookOrderDto boDto = new BookOrderDto(12000,"Processing",8219,"Cash","Ashish","9778989898",8219);
		
		BookOrder boResponse1 = bookOrderServ.addBookOrder(boDto);
		BookOrder boResponse2 = bookOrderServ.addBookOrder(boDto1);
		
		List<BookOrder> bookOrderListResponse = bookOrderServ.viewBookOrderByCity("Raipur");
		//assertEquals(19,bookOrderListResponse.size());
		assertThat(bookOrderListResponse.size()).isGreaterThan(0);
		
		
	}
	
	@Test
	void testViewBookOrderByCountry() throws Exception {
		
		Address add1 = new Address(8219,"1/29 D Block","Raipur","India","492001"); 
		LocalDate date1 = LocalDate.parse( "2021-08-06");
		LocalDate date2 = LocalDate.now();
		Customer cust1 = new Customer(8219,"abc@gmail.com","Ashish","string","8989898989",date1);
		
		BookOrderDto boDto1 = new BookOrderDto(10000,"Processing",8219,"Cash","Akash","9898989898",8219);
		BookOrderDto boDto = new BookOrderDto(12000,"Processing",8219,"Cash","Ashish","9778989898",8219);
		
		BookOrder boResponse1 = bookOrderServ.addBookOrder(boDto);
		BookOrder boResponse2 = bookOrderServ.addBookOrder(boDto);
		
		List<BookOrder> bookOrderListResponse = bookOrderServ.viewBookOrderByCountry("India");
		assertThat(bookOrderListResponse.size()).isGreaterThan(0);
		
	}
	
	@Test
	void testViewBookOrderByPincode() throws Exception {
		
		Address add1 = new Address(8219,"1/29 D Block","Raipur","India","492001"); 
		LocalDate date1 = LocalDate.parse( "2021-08-06");
		LocalDate date2 = LocalDate.now();
		Customer cust1 = new Customer(8219,"abc@gmail.com","Ashish","string","8989898989",date1);
		
		BookOrderDto boDto1 = new BookOrderDto(10000,"Processing",8219,"Cash","Akash","9898989898",8219);
		BookOrderDto boDto = new BookOrderDto(12000,"Processing",8219,"Cash","Ashish","9778989898",8219);
		
		BookOrder boResponse1 = bookOrderServ.addBookOrder(boDto);
		BookOrder boResponse2 = bookOrderServ.addBookOrder(boDto);
		
		List<BookOrder> bookOrderListResponse = bookOrderServ.viewBookOrderByPincode("492001");
		assertThat(bookOrderListResponse.size()).isGreaterThan(0);
		
	}
	@Test
	void testViewBookOrderByCustomerId() throws Exception {
		
		Address add1 = new Address(8219,"1/29 D Block","Raipur","India","492001"); 
		LocalDate date1 = LocalDate.parse( "2021-08-06");
		LocalDate date2 = LocalDate.now();
		Customer cust1 = new Customer(8219,"abc@gmail.com","Ashish","string","8989898989",date1);
		
		BookOrderDto boDto1 = new BookOrderDto(10000,"Processing",8219,"Cash","Akash","9898989898",8219);
		BookOrderDto boDto = new BookOrderDto(12000,"Processing",8219,"Cash","Ashish","9778989898",8219);
		
		BookOrder boResponse1 = bookOrderServ.addBookOrder(boDto);
		BookOrder boResponse2 = bookOrderServ.addBookOrder(boDto);
		
		List<BookOrder> bookOrderListResponse = bookOrderServ.viewBookOrderByCustomerId(8219);
		assertThat(bookOrderListResponse.size()).isGreaterThan(0);
		
	}
	
	@Test
	void testViewBookOrderByCustomerName() throws Exception {
		
		Address add1 = new Address(8219,"1/29 D Block","Raipur","India","492001"); 
		LocalDate date1 = LocalDate.parse( "2021-08-06");
		LocalDate date2 = LocalDate.now();
		Customer cust1 = new Customer(8219,"abc@gmail.com","Ashish","string","8989898989",date1);
		
		BookOrderDto boDto1 = new BookOrderDto(10000,"Processing",8219,"Cash","Akash","9898989898",8219);
		BookOrderDto boDto = new BookOrderDto(12000,"Processing",8219,"Cash","Ashish","9778989898",8219);
		
		BookOrder boResponse1 = bookOrderServ.addBookOrder(boDto);
		BookOrder boResponse2 = bookOrderServ.addBookOrder(boDto);
		
		List<BookOrder> bookOrderListResponse = bookOrderServ.viewBookOrderByCustomerName("Ashish");
		assertThat(bookOrderListResponse.size()).isGreaterThan(0);
		
	}
}
