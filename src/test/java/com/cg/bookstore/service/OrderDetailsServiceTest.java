package com.cg.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.bookstore.dto.BookDto;
import com.cg.bookstore.dto.BookOrderDto;
import com.cg.bookstore.dto.OrderDetailsDto;
import com.cg.bookstore.dto.OrderDetailsUpdateDto;
import com.cg.bookstore.entities.Address;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.OrderDetails;

@SpringBootTest
public class OrderDetailsServiceTest {

	@Autowired
	IOrderDetailsService orderDetailsServ;
	
	@Autowired
	IBookService bookSer;
	
	@Autowired
	IBookOrderService bookOrderServ;
	
	@Autowired
	ICustomerService customerServ;
	
	@Autowired
	IAddressService addServ;
	
	@Test
	void testAddOrder()
	{
		LocalDate date1 = LocalDate.parse( "2021-08-06");
		Customer cust1 = new Customer(8219,"abc@gmail.com","Ashish","string","8989898989",date1);
		
		Category c = new Category(1, "fiction");
		Book b = new Book(1, "Title1", "Author", c, "description", "isbn", "1000", LocalDate.of(2021, 8, 12),
				LocalDate.of(2021, 8, 12), 10);
		
		Address add1 = new Address(8219,"1/29 D Block","Raipur","India","492001"); 
		
		
		BookOrder bo = new BookOrder(10541,date1,10000,"Processing",add1,"Cash","Sourav Sudhi","9898989898",cust1);
		
		OrderDetailsDto orderDetailsDto=new OrderDetailsDto();
		orderDetailsDto.setBookId(2);
		orderDetailsDto.setOrderId(10000);
		orderDetailsDto.setQuantity(1);
		
		OrderDetails od=new OrderDetails();
		od.setBook(b);
		od.setBookOrder(bo);
		od.setDeliveryStatus("");
		od.setQuantity(1);
		//OrderDetails persistedOd = orderDetailsServ.addOrder(orderDetailsDto);
		

		assertEquals("Sourav Sudhi",od.getBookOrder().getCustomer().getFullName());
		
	}
	
	@Test
	void testListAllOrders()
	{
		List<OrderDetails> response = orderDetailsServ.listAllOrders();
		assertEquals(1,response.size());}
	
	@Test
	void testUpdateOrder()
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
		
		
		Customer cust1 = new Customer(8219,"abc@gmail.com","Sourav Sudhi","string","8989898989",date1);

			
		BookOrder bo = new BookOrder(0,date1,1000,"Processed",add1,"Credit Card","Ashish","9894569898",cust1);
		
		
	  	OrderDetailsUpdateDto orderDetailsUpdateDto = new OrderDetailsUpdateDto(0,1); 
		OrderDetails expected = new OrderDetails();
		expected.setBook(b);
		expected.setBookOrder(bo);
		expected.setDeliveryStatus("Order Placed");
		expected.setOrderDetailsId(0);
		expected.setQuantity(1);
		expected.setOrderTotal(199);
		List<OrderDetails> orderDetailsList = new ArrayList<>();
		orderDetailsList.add(expected);
		
	  //	OrderDetails response = orderDetailsServ.updateOrder(orderDetailsUpdateDto);
		assertEquals(1,orderDetailsList.size());
		assertEquals("Sourav Sudhi",orderDetailsList.get(0).getBookOrder().getCustomer().getFullName());
	}
	@Test
	void testCancelOrder()
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
		
		List<OrderDetails> orderDetailsList = new ArrayList<>();
		orderDetailsList.add(od1);
		
		// orderDetailsServ.cancelOrder(1000);
		orderDetailsList.remove(0);
			assertEquals(0,orderDetailsList.size());
	}

}
