package com.cg.bookstore.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.bookstore.dto.BookOrderDto;
import com.cg.bookstore.dto.BookOrderUpdateDto;
import com.cg.bookstore.entities.Address;
import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.repository.IAddressRepository;
import com.cg.bookstore.repository.IBookOrderRepository;
import com.cg.bookstore.repository.ICustomerRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class BookOrderServiceMockitoTest {
	
	//INJECTING MOCK FOR BOOK ORDER SERVICE CLASS
	@InjectMocks
	BookOrderServiceImpl bookOrderServ;
	
	//INJECTING EXTERNAL REPOSITORY CLASS AS MOCK BEAN
	@MockBean
	IBookOrderRepository bookOrderRepo;
	
	@MockBean
	IAddressRepository addressRepo;
	
	@MockBean 
	ICustomerRepository customerRepo;
	
	@BeforeEach
	public void setup(){
	    MockitoAnnotations.openMocks(this); //without this you will get NPE
	}
	
	@Test
	void testListAllBookOrders() {
		
		//INITIALISING ADDRESS
		Address add1 = new Address(101,"1/29 D Block","Raipur","India","492001"); 
		Address add2 = new Address(102,"1/29 A Block","Bhilai","India","492003");
		
		//INITIALISING DATES
		LocalDate date1= LocalDate.parse( "2021-08-06");
		LocalDate date2= LocalDate.parse( "2021-08-08");
		
		//INITITALISING CUSTOMERS
		Customer cust1 = new Customer(101,"abc@gmail.com","Ashish","abc123","8989898989",date1);
		Customer cust2 = new Customer(102,"cba@gmail.com","Bunny","123abc","8989898989",date2);
		
		//INITIALISING BOOK ORDERS
		BookOrder bo1 = new BookOrder(101,date2,10000,"Processing",add1,"Cash","Akash","9898989898",cust1);
		BookOrder bo2 = new BookOrder(102,date1,10000,"Processed",add2,"Card","Bunty","7898989898",cust2);
		
		//MAKING A NEW BOOK ORDER LIST WITH RECENTLY CREATED BOOK ORDER OBJECTS
		List<BookOrder> bookOrderList = new ArrayList<>();
		bookOrderList.add(bo1);
		bookOrderList.add(bo2);
		
		//MAKING MOCKITO RETURN bookOrderList WHEN findAll() FROM REPOSITORY IS CALLLED
		Mockito.when(bookOrderRepo.findAll()).thenReturn(bookOrderList);
		
		//CALLING SERVICE METHOD TO RETURN LIST OF ALL BOOK ORDERS
		List<BookOrder> bookOrders = bookOrderServ.listAllBookOrders();
		
		//SIZE SHOULD BE 2 FOR CONDITION TO PASS
		assertEquals(2,bookOrders.size());
			
	}
   
	@Test
	void testGetBookOrderById() {
		
		//INITIALISING ADDRESS
	    Address add1 = new Address(101,"1/29 D Block","Raipur","India","492001"); 
		Address add2 = new Address(102,"1/29 A Block","Bhilai","India","492003");
				
		//INITIALISING DATES
		LocalDate date1= LocalDate.parse( "2021-08-06");
		LocalDate date2= LocalDate.parse( "2021-08-08");
			
		//INITITALISING CUSTOMERS
		Customer cust1 = new Customer(101,"abc@gmail.com","Ashish","abc123","8989898989",date1);
		Customer cust2 = new Customer(102,"cba@gmail.com","Bunny","123abc","8989898989",date2);
				
		//INITIALISING BOOK ORDERS
		Optional<BookOrder> bo1 = Optional.ofNullable(new BookOrder(101,date2,10000,"Processing",add1,"Cash","Akash","9898989898",cust1));
		BookOrder bo2 = new BookOrder(102,date1,10000,"Processed",add2,"Card","Bunty","7898989898",cust2);
				
		//MAKING A NEW BOOK ORDER LIST WITH RECENTLY CREATED BOOK ORDER OBJECTS
		List<BookOrder> bookOrderList = new ArrayList<>();
		bookOrderList.add(bo1.get());
		bookOrderList.add(bo2);
		
		//MAKING MOCKITO RETURN bo1 WHEN ASKED FOR REPOSITORY findById method
		Mockito.when(bookOrderRepo.findById(101)).thenReturn(bo1);
		
		BookOrder bookOrder = bookOrderServ.getBookOrderById(101);
		
		//MATCHING FIELDS 
		assertEquals(cust1,bookOrder.getCustomer());
		assertEquals(date2,bookOrder.getOrderDate());
		assertEquals(10000,bookOrder.getOrderTotal());
		assertEquals("Processing",bookOrder.getStatus());
		assertEquals(add1,bookOrder.getShippingAddress());
		assertEquals("Cash",bookOrder.getPaymentMethod());
		assertEquals("Akash",bookOrder.getRecipientName());
		assertEquals("9898989898",bookOrder.getRecipientPhone());
		
	}
	
	@Test
	void testAddBookOrder() throws Exception {
		
		//INITIALISING ADDRESS
	    Address add1 = new Address(101,"1/29 D Block","Raipur","India","492001");
	    //INITIALISING DATE
	  	LocalDate date1= LocalDate.now();
	    //INITITALISING CUSTOMER
	  	Customer cust1 = new Customer(101,"abc@gmail.com","Ashish","abc123","8989898989",date1);
		//INITIALISING BOOK ORDER
	  	BookOrder bo1 = new BookOrder(0,date1,10000,"Processing",add1,"Cash","Akash","9898989898",cust1);
	  	
	  	//INITIALISING DTO
	  	BookOrderDto boDto = new BookOrderDto(10000,"Processing",101,"Cash","Akash","9898989898",101);
	  	
	  	//RETURNING bo1 OBJECT WHEN REPOSITORY METHOD FOR ADDING NEW ORDER IS CALLED 
	  	Mockito.when(addressRepo.findById(101)).thenReturn(Optional.ofNullable(add1));
	  	Mockito.when(customerRepo.findById(101)).thenReturn(Optional.ofNullable(cust1));
	    Mockito.when(bookOrderRepo.save(bo1)).thenReturn(bo1);
	  	
	 	BookOrder bookOrder = bookOrderServ.addBookOrder(boDto);
	 	
	 	assertEquals(bo1,bookOrder);
	  
		
	}
	
	@Test
	void testCancelBookOrder(){
		
		//INITIALISING ADDRESS
		Address add1 = new Address(101,"1/29 D Block","Raipur","India","492001"); 
		Address add2 = new Address(102,"1/29 A Block","Bhilai","India","492003");
				
		//INITIALISING DATES
		LocalDate date1= LocalDate.parse( "2021-08-06");
		LocalDate date2= LocalDate.parse( "2021-08-08");
				
		//INITITALISING CUSTOMERS
		Customer cust1 = new Customer(101,"abc@gmail.com","Ashish","abc123","8989898989",date1);
		Customer cust2 = new Customer(102,"cba@gmail.com","Bunny","123abc","8989898989",date2);
				
		//INITIALISING BOOK ORDERS
		BookOrder bo1 = new BookOrder(101,date2,10000,"Processing",add1,"Cash","Akash","9898989898",cust1);
		BookOrder bo2 = new BookOrder(102,date1,10000,"Processed",add2,"Card","Bunty","7898989898",cust2);
				
		//MAKING A NEW BOOK ORDER LIST WITH RECENTLY CREATED BOOK ORDER OBJECTS
		List<BookOrder> bookOrderList = new ArrayList<>();
		bookOrderList.add(bo1);
		bookOrderList.add(bo2);
				
		//MAKING MOCKITO RETURN bookOrderList WHEN findAll() FROM REPOSITORY IS CALLLED
		Mockito.when(bookOrderRepo.findById(101)).thenReturn(Optional.of(bo1));
		//doNothing().when(bookOrderRepo).delete(bo1);
		verify(bookOrderRepo,times(1)).delete(bo1);
		//CALLING SERVICE METHOD TO RETURN LIST OF ALL BOOK ORDERS
		bookOrderServ.cancelBookOrderById(bo1.getOrderId());
				
				
		
	}
	
	@Test
	void testViewBookOrderByCity() {
		
		//INITIALISING ADDRESS
		Address add1 = new Address(101,"1/29 D Block","Raipur","India","492001"); 
		Address add2 = new Address(102,"1/29 A Block","Bhilai","India","492003");
						
		//INITIALISING DATES
		LocalDate date1= LocalDate.parse( "2021-08-06");
		LocalDate date2= LocalDate.parse( "2021-08-08");
					
		//INITITALISING CUSTOMERS		
		Customer cust1 = new Customer(101,"abc@gmail.com","Ashish","abc123","8989898989",date1);
		Customer cust2 = new Customer(102,"cba@gmail.com","Bunny","123abc","8989898989",date2);
						
		//INITIALISING BOOK ORDERS
		BookOrder bo1 = new BookOrder(101,date2,10000,"Processing",add1,"Cash","Akash","9898989898",cust1);
		BookOrder bo2 = new BookOrder(102,date1,10000,"Processed",add2,"Card","Bunty","7898989898",cust2);
		BookOrder bo3 = new BookOrder(103,date1,2000,"Processed",add1,"Cash","Akash","9898989898",cust1);
		//MAKING A NEW BOOK ORDER LIST WITH RECENTLY CREATED BOOK ORDER OBJECTS
		List<BookOrder> bookOrderList = new ArrayList<>();
		bookOrderList.add(bo1);
		//bookOrderList.add(bo2);
		bookOrderList.add(bo3);
		
		Mockito.when(bookOrderRepo.viewBookOrderByCity("Raipur")).thenReturn(bookOrderList);
		
        List<BookOrder> responseBookOrderList = bookOrderServ.viewBookOrderByCity("Raipur");
		
		assertEquals(2,responseBookOrderList.size());
		assertEquals("Raipur",responseBookOrderList.get(0).getShippingAddress().getCity());
		assertEquals("Raipur",responseBookOrderList.get(1).getShippingAddress().getCity());
			
		
	}
	@Test
	void testViewBookOrderByCountry() {
		
		//INITIALISING ADDRESS
		Address add1 = new Address(101,"1/29 D Block","Raipur","India","492001"); 
		Address add2 = new Address(102,"1/29 A Block","Bhilai","India","492003");
						
		//INITIALISING DATES
		LocalDate date1= LocalDate.parse( "2021-08-06");
		LocalDate date2= LocalDate.parse( "2021-08-08");
					
		//INITITALISING CUSTOMERS		
		Customer cust1 = new Customer(101,"abc@gmail.com","Ashish","abc123","8989898989",date1);
		Customer cust2 = new Customer(102,"cba@gmail.com","Bunny","123abc","8989898989",date2);
						
		//INITIALISING BOOK ORDERS
		BookOrder bo1 = new BookOrder(101,date2,10000,"Processing",add1,"Cash","Akash","9898989898",cust1);
		BookOrder bo2 = new BookOrder(102,date1,10000,"Processed",add2,"Card","Bunty","7898989898",cust2);
		BookOrder bo3 = new BookOrder(103,date1,2000,"Processed",add1,"Cash","Akash","9898989898",cust1);
		//MAKING A NEW BOOK ORDER LIST WITH RECENTLY CREATED BOOK ORDER OBJECTS
		List<BookOrder> bookOrderList = new ArrayList<>();
		bookOrderList.add(bo1);
		//bookOrderList.add(bo2);
		bookOrderList.add(bo3);
		
		Mockito.when(bookOrderRepo.viewBookOrderByCountry("India")).thenReturn(bookOrderList);
		
		List<BookOrder> responseBookOrderList = bookOrderServ.viewBookOrderByCountry("India");
		
		assertEquals(2,responseBookOrderList.size());
		assertEquals("India",responseBookOrderList.get(0).getShippingAddress().getCountry());
		assertEquals("India",responseBookOrderList.get(1).getShippingAddress().getCountry());
			
	}
	
	@Test
	void testViewBookOrderByPincode() {
		
		//INITIALISING ADDRESS
		Address add1 = new Address(101,"1/29 D Block","Raipur","India","492001"); 
		Address add2 = new Address(102,"1/29 A Block","Bhilai","India","492003");
						
		//INITIALISING DATES
		LocalDate date1= LocalDate.parse( "2021-08-06");
		LocalDate date2= LocalDate.parse( "2021-08-08");
					
		//INITITALISING CUSTOMERS		
		Customer cust1 = new Customer(101,"abc@gmail.com","Ashish","abc123","8989898989",date1);
		Customer cust2 = new Customer(102,"cba@gmail.com","Bunny","123abc","8989898989",date2);
						
		//INITIALISING BOOK ORDERS
		BookOrder bo1 = new BookOrder(101,date2,10000,"Processing",add1,"Cash","Akash","9898989898",cust1);
		BookOrder bo2 = new BookOrder(102,date1,10000,"Processed",add2,"Card","Bunty","7898989898",cust2);
		BookOrder bo3 = new BookOrder(103,date1,2000,"Processed",add1,"Cash","Akash","9898989898",cust1);
		//MAKING A NEW BOOK ORDER LIST WITH RECENTLY CREATED BOOK ORDER OBJECTS
		List<BookOrder> bookOrderList = new ArrayList<>();
		bookOrderList.add(bo1);
		//bookOrderList.add(bo2);
		//bookOrderList.add(bo3);
		
		Mockito.when(bookOrderRepo.viewBookOrderByPincode("492001")).thenReturn(bookOrderList);
		
		List<BookOrder> responseBookOrderList = bookOrderServ.viewBookOrderByPincode("492001");
				
		assertEquals(1,responseBookOrderList.size());
		assertEquals("492001",responseBookOrderList.get(0).getShippingAddress().getPincode());
			
	}
	
	@Test
	void testViewBookOrderByCustomerId() {
		
		//INITIALISING ADDRESS
		Address add1 = new Address(101,"1/29 D Block","Raipur","India","492001"); 
		Address add2 = new Address(102,"1/29 A Block","Bhilai","India","492003");
						
		//INITIALISING DATES
		LocalDate date1= LocalDate.parse( "2021-08-06");
		LocalDate date2= LocalDate.parse( "2021-08-08");
					
		//INITITALISING CUSTOMERS		
		Customer cust1 = new Customer(101,"abc@gmail.com","Ashish","abc123","8989898989",date1);
		Customer cust2 = new Customer(102,"cba@gmail.com","Bunny","123abc","8989898989",date2);
						
		//INITIALISING BOOK ORDERS
		BookOrder bo1 = new BookOrder(101,date2,10000,"Processing",add1,"Cash","Akash","9898989898",cust1);
		BookOrder bo2 = new BookOrder(102,date1,10000,"Processed",add2,"Card","Bunty","7898989898",cust2);
		BookOrder bo3 = new BookOrder(103,date1,2000,"Processed",add1,"Cash","Akash","9898989898",cust1);
		//MAKING A NEW BOOK ORDER LIST WITH RECENTLY CREATED BOOK ORDER OBJECTS
		List<BookOrder> bookOrderList = new ArrayList<>();
		bookOrderList.add(bo1);
		//bookOrderList.add(bo2);
		//bookOrderList.add(bo3);
		
		Mockito.when(bookOrderRepo.viewBookOrderByCustomerId(101)).thenReturn(bookOrderList);
		
		List<BookOrder> responseBookOrderList = bookOrderServ.viewBookOrderByCustomerId(101);
		
		assertEquals(1,responseBookOrderList.size());
		assertEquals(101,responseBookOrderList.get(0).getCustomer().getCustomerId());
			
	}
	
	@Test
	void testViewBookOrderByCustomerName() {
		
		//INITIALISING ADDRESS
		Address add1 = new Address(101,"1/29 D Block","Raipur","India","492001"); 
		Address add2 = new Address(102,"1/29 A Block","Bhilai","India","492003");
						
		//INITIALISING DATES
		LocalDate date1= LocalDate.parse( "2021-08-06");
		LocalDate date2= LocalDate.parse( "2021-08-08");
					
		//INITITALISING CUSTOMERS		
		Customer cust1 = new Customer(101,"abc@gmail.com","Ashish","abc123","8989898989",date1);
		Customer cust2 = new Customer(102,"cba@gmail.com","Bunny","123abc","8989898989",date2);
						
		//INITIALISING BOOK ORDERS
		BookOrder bo1 = new BookOrder(101,date2,10000,"Processing",add1,"Cash","Akash","9898989898",cust1);
		BookOrder bo2 = new BookOrder(102,date1,10000,"Processed",add2,"Card","Bunty","7898989898",cust2);
		BookOrder bo3 = new BookOrder(103,date1,2000,"Processed",add1,"Cash","Akash","9898989898",cust1);
		//MAKING A NEW BOOK ORDER LIST WITH RECENTLY CREATED BOOK ORDER OBJECTS
		List<BookOrder> bookOrderList = new ArrayList<>();
		bookOrderList.add(bo1);
		//bookOrderList.add(bo2);
		//bookOrderList.add(bo3);
		
		Mockito.when(bookOrderRepo.viewBookOrderByCustomerName("Ashish")).thenReturn(bookOrderList);
		
		List<BookOrder> responseBookOrderList = bookOrderServ.viewBookOrderByCustomerName("Ashish");
		
		assertEquals(1,bookOrderList.size());
		assertEquals("Ashish",responseBookOrderList.get(0).getCustomer().getFullName());
			
	}
	
	@Test
	void testViewTotalBookOrderByCustomer() {
		
		
	}
	
	@Test
	void testUpdateBookOrder() {
        
		//INITIALISING ADDRESS
		Address add1 = new Address(101,"1/29 D Block","Raipur","India","492001"); 
		//INITIALISING DATES
		LocalDate date1= LocalDate.parse( "2021-08-06");
		//INITITALISING CUSTOMERS		
		Customer cust1 = new Customer(101,"abc@gmail.com","Ashish","abc123","8989898989",date1);	
		//INITIALISING BOOK ORDERS
		BookOrder bo1 = new BookOrder(0,date1,1000,"Processed",add1,"Credit Card","Ashish","9894569898",cust1);
		BookOrder expected = new BookOrder(0,date1,10000,"Processing",add1,"Cash","Akash","9898989898",cust1);
		
		BookOrderUpdateDto bookOrderdto = new BookOrderUpdateDto(10000,"Processing",101,"Cash","Akash","9898989898");
		
		Mockito.when(bookOrderRepo.findById(0)).thenReturn(Optional.ofNullable(bo1));
		Mockito.when(addressRepo.findById(101)).thenReturn(Optional.ofNullable(add1));
		
		BookOrder response = bookOrderServ.updateBookOrder(0, bookOrderdto);
		assertEquals(expected,response);
	
		
    
	}
}
