package com.cg.bookstore.service;

import java.util.List;
import java.util.Optional;

import com.cg.bookstore.dto.BookOrderDto;
import com.cg.bookstore.dto.BookOrderTotalDto;
import com.cg.bookstore.dto.BookOrderUpdateDto;
import com.cg.bookstore.entities.BookOrder;
//import com.cg.bookstore.entities.BookOrderDto;
import com.cg.bookstore.entities.Customer;

public interface IBookOrderService {
    //BASIC CRUD 
	public List<BookOrder> listAllBookOrders(); //done
	public BookOrder getBookOrderById(int id);//done
	public BookOrder addBookOrder(BookOrderDto bookOrder);
	public void cancelBookOrderById(int id);//done
	public BookOrder updateBookOrder(int bookOrderId, BookOrderUpdateDto bookOrderDto);
	public BookOrder updateTotalForId(int id , Double total);
	//WHERE CLAUSE QUERY METHODS
	public List<BookOrder> viewBookOrderByCity(String city);//done
	public List<BookOrder> viewBookOrderByPincode(String pincode);//done
	public List<BookOrder> viewBookOrderByCountry(String country);//done
	public List<BookOrder> viewBookOrderByCustomerId(int id);//done
	public List<BookOrder> viewBookOrderByCustomerName(String name);
	//GROUP BY ORDER BY QUERY METHODS
    public List<BookOrderTotalDto> viewTotalBookOrderByCustomer();
	public List<BookOrderTotalDto> viewTotalBookOrderByCustomerSortByTotal();
	
}