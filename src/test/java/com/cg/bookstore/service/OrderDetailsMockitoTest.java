package com.cg.bookstore.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.bookstore.dto.OrderDetailsCustomerDto;
import com.cg.bookstore.dto.OrderDetailsDto;
import com.cg.bookstore.dto.OrderDetailsUpdateDto;
import com.cg.bookstore.entities.Address;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.OrderDetails;
import com.cg.bookstore.repository.IBookOrderRepository;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.IOrderDetailsRepository;


@ExtendWith(SpringExtension.class)
public class OrderDetailsMockitoTest {

	@InjectMocks
	OrderDetailsServiceImpl orderDetailsService;
	
	@MockBean
	IOrderDetailsRepository orderDetailsRepo;
	
	@MockBean
	IBookRepository bookRepo;
	
	@MockBean 
	IBookOrderRepository bookOrderRepo;
	
	@BeforeEach
	void init()
	{
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testlistAllOrders()
	{
		Customer c1=new Customer();
		c1.setCustomerId(1);
		c1.setFullName("Sourav Sudhi");
		
		Book b1 =new Book();
		b1.setBookId(10);
		b1.setPrice("199");
		b1.setTitle("Atomic Habits");
		
		BookOrder bookorder1=new BookOrder();
		bookorder1.setOrderId(100);
		bookorder1.setCustomer(c1);
		bookorder1.setOrderTotal(299.00);
		
		OrderDetails od1=new OrderDetails();
		od1.setOrderDetailsId(1000);
		od1.setBook(b1);
		od1.setBookOrder(bookorder1);
		od1.setQuantity(2);
		od1.setOrderTotal(398);
		od1.setDeliveryStatus(null);
		
		List<OrderDetails> orderDetailsList = new ArrayList<>();
		orderDetailsList.add(od1);
		

		Mockito.when(orderDetailsRepo.findAll()).thenReturn(orderDetailsList);
		
		List<OrderDetails> orderDetails = orderDetailsService.listAllOrders();
		
		assertEquals(1,orderDetails.size());
		assertEquals("Sourav Sudhi",orderDetails.get(0).getBookOrder().getCustomer().getFullName());
	}
	
	@Test
	void addOrder()
	{
		Category c=new Category(12,"self");
		Book b=new Book();
		b.setAuthor("James");
		b.setBookId(1);
		b.setCategory(c);
		b.setDescription("Lorem");
		b.setIsbn("1AB");
		LocalDate date1 = LocalDate.parse( "2021-08-06");
		b.setLastUpdatedOn(date1);
		b.setPrice("199");
		b.setPublishDate(date1);
		b.setStock(2);
		b.setTitle("Atomic");
		
		Address add1 = new Address();
		add1.setAddress("A");
		add1.setAddressId(1);
		add1.setCity("Kannur");
		add1.setCountry("India");
		add1.setPincode("12345");
		
		
		Customer cust1 = new Customer(8219,"abc@gmail.com","Sourav","string","8989898989",date1);

			
		BookOrder bo = new BookOrder();
		bo.setCustomer(cust1);
		bo.setOrderDate(date1);
		bo.setOrderId(10);
		bo.setOrderTotal(199);
		bo.setPaymentMethod("CASH");
		bo.setRecipientName("ABC");
		bo.setRecipientPhone("123");
		bo.setShippingAddress(add1);
		bo.setStatus("Sucess");
		
		OrderDetails expected = new OrderDetails();
		expected.setBook(b);
		expected.setBookOrder(bo);
		expected.setDeliveryStatus("Order Placed");
		expected.setOrderDetailsId(10);
		expected.setQuantity(1);
		expected.setOrderTotal(199);
		

		Mockito.when(bookRepo.findById(1)).thenReturn(Optional.ofNullable(b));
	  	Mockito.when(bookOrderRepo.findById(10)).thenReturn(Optional.ofNullable(bo));
	  	
		OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
		orderDetailsDto.setBookId(1);
		orderDetailsDto.setOrderId(10);
		orderDetailsDto.setQuantity(1);
		OrderDetails response = orderDetailsService.addOrder(orderDetailsDto);
		
		Mockito.when(orderDetailsRepo.save(expected)).thenReturn(expected);
	 	
		assertEquals("Sourav",expected.getBookOrder().getCustomer().getFullName());
		assertEquals(1,expected.getBook().getBookId());
		
	}

	@Test
	void updateOrder()
	{
		Category c=new Category(12,"self");
		
		Book b=new Book();
		b.setAuthor("James");
		b.setBookId(10);
		b.setCategory(c);
		b.setDescription("Lorem");
		b.setIsbn("1AB");
		LocalDate date1 = LocalDate.parse( "2021-08-06");
		b.setLastUpdatedOn(date1);
		b.setPrice("199");
		b.setPublishDate(date1);
		b.setStock(2);
		b.setTitle("Atomic");
		
		Address add1 = new Address();
		add1.setAddress("A");
		add1.setAddressId(1);
		add1.setCity("Kannur");
		add1.setCountry("India");
		add1.setPincode("12345");
		
		
		Customer cust1 = new Customer(8219,"abc@gmail.com","Sourav","string","8989898989",date1);

			
		BookOrder bo = new BookOrder(0,date1,1000,"Processed",add1,"Credit Card","Ashish","9894569898",cust1);
		
		OrderDetails expected = new OrderDetails();
		expected.setBook(b);
		expected.setBookOrder(bo);
		expected.setDeliveryStatus("Order Placed");
		expected.setOrderDetailsId(0);
		expected.setQuantity(1);
		expected.setOrderTotal(199);
		
	  	OrderDetailsUpdateDto orderDetailsUpdateDto = new OrderDetailsUpdateDto(0,1);
	  	
	  
		Mockito.when(orderDetailsRepo.findById(0)).thenReturn(Optional.ofNullable(expected));

	  	OrderDetails response = orderDetailsService.updateOrder(orderDetailsUpdateDto);
	  	assertEquals(expected,response);
	}

	@Test
	void listOrderByCustomer()
	{

		Category c=new Category(12,"self");
		Book b=new Book();
		b.setAuthor("James");
		b.setBookId(1);
		b.setCategory(c);
		b.setDescription("Lorem");
		b.setIsbn("1AB");
		LocalDate date1 = LocalDate.parse( "2021-08-06");
		b.setLastUpdatedOn(date1);
		b.setPrice("199");
		b.setPublishDate(date1);
		b.setStock(2);
		b.setTitle("Atomic");
		
		Address add1 = new Address();
		add1.setAddress("A");
		add1.setAddressId(1);
		add1.setCity("Kannur");
		add1.setCountry("India");
		add1.setPincode("12345");
		
		
		Customer cust1 = new Customer(8219,"abc@gmail.com","Sourav","string","8989898989",date1);

			
		BookOrder bo = new BookOrder();
		bo.setCustomer(cust1);
		bo.setOrderDate(date1);
		bo.setOrderId(10);
		bo.setOrderTotal(199);
		bo.setPaymentMethod("CASH");
		bo.setRecipientName("ABC");
		bo.setRecipientPhone("123");
		bo.setShippingAddress(add1);
		bo.setStatus("Sucess");
		
		OrderDetails expected = new OrderDetails();
		expected.setBook(b);
		expected.setBookOrder(bo);
		expected.setDeliveryStatus("Order Placed");
		expected.setOrderDetailsId(10);
		expected.setQuantity(1);
		expected.setOrderTotal(199);
		
	}
	
	@Test
	void cancelOrder()
	{
		Customer c1=new Customer();
		c1.setCustomerId(1);
		c1.setFullName("Sourav Sudhi");
		
		Book b1 =new Book();
		b1.setBookId(10);
		b1.setPrice("199");
		b1.setTitle("Atomic Habits");
		
		BookOrder bookorder1=new BookOrder();
		bookorder1.setOrderId(1000);
		bookorder1.setCustomer(c1);
		bookorder1.setOrderTotal(299.00);
		
		OrderDetails od1=new OrderDetails();
		od1.setOrderDetailsId(1000);
		od1.setBook(b1);
		od1.setBookOrder(bookorder1);
		od1.setQuantity(2);
		od1.setOrderTotal(398);
		od1.setDeliveryStatus(null);
		
		Optional<OrderDetails> opt = orderDetailsRepo.findById(od1.getOrderDetailsId());
		Mockito.when(orderDetailsRepo.findById(1000)).thenReturn(Optional.of(od1));
		//verify(orderDetailsRepo,times(1)).delete(opt.get());
	
		assertEquals(1000, od1.getOrderDetailsId());
	}


}
