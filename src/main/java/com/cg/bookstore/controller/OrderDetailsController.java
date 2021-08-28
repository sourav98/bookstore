package com.cg.bookstore.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.dto.OrderDetailsBookDto;
import com.cg.bookstore.dto.OrderDetailsCustomerDto;
import com.cg.bookstore.dto.OrderDetailsDto;
import com.cg.bookstore.dto.OrderDetailsUpdateDto;
import com.cg.bookstore.entities.OrderDetails;
import com.cg.bookstore.service.IOrderDetailsService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Validated
public class OrderDetailsController
{

	final static Logger LOGGER = LogManager.getLogger(OrderDetailsController.class);
	@Autowired
	IOrderDetailsService orderDetailsServ;

	// list by admin 
	
//	@GetMapping("/orderdetails/all/{userId}")
//	public ResponseEntity<List<OrderDetails>>viewOrdersForAdmin(@PathVariable("userId") int userId)
//	{
//		LOGGER.info("Fetching all order details by get mapping using OrderDetails Controller");
//		return new ResponseEntity<>(orderDetailsServ.viewOrdersForAdmin(userId), HttpStatus.OK);
//	}

	
	// 1 List All Orders

	@GetMapping("/orderdetails/all")
	public ResponseEntity<List<OrderDetails>> listAllOrders()
	{
		LOGGER.info("Fetching all order details by get mapping using OrderDetails Controller");
		return new ResponseEntity<>(orderDetailsServ.listAllOrders(), HttpStatus.OK);
	}

	// 2 List Order By Customer

	@GetMapping("/orderdetails/customer/{customerId}")
	public ResponseEntity<List<OrderDetailsCustomerDto>> listOrderByCustomer(@PathVariable("customerId") int customerId)
	{
		LOGGER.info("Fetching particular order detail using id by get mapping using OrderDetails Controller");
		return new ResponseEntity<>(orderDetailsServ.listOrderByCustomer(customerId), HttpStatus.OK);

	}

	// 5 Cancel Order

	@DeleteMapping("/orderdetails/{orderId}")
	public ResponseEntity<OrderDetails> cancelOrder(@PathVariable("orderId") int orderId)
	{
		LOGGER.info("Deleting particular order detail by delete mapping using OrderDetails Controller");
		orderDetailsServ.cancelOrder(orderId);
		return ResponseEntity.noContent().build();

	}
	// 6 Add Order

	@PostMapping("/orderdetails")
	public OrderDetails addOrder(@Valid @RequestBody OrderDetailsDto orderDetailsDto)
	{
		LOGGER.info("Adding new order detail by post mapping using OrderDetails Controller");
		return orderDetailsServ.addOrder(orderDetailsDto);
	}

	// 7 Update Order

	@PutMapping("/orderdetails/{orderId}")
	public OrderDetails updateOrder(@PathVariable("orderId") int orderId, @RequestBody OrderDetailsUpdateDto orderDetailsUpdateDto)
	{
		LOGGER.info("Updating particular order detail by put mapping using OrderDetails Controller");
		return orderDetailsServ.updateOrder(orderId,orderDetailsUpdateDto);
	}

	// 8 View Order By Book
	@GetMapping("/orderdetails/book/{bookId}")
	public List<OrderDetailsBookDto> viewOrderByBook(@PathVariable("bookId") int bookId)
	{
		LOGGER.info("Fetching particular order detail using book id by get mapping using OrderDetails Controller");
		return orderDetailsServ.viewOrderByBook(bookId);
	}

	@GetMapping("/orderdetails/{orderId}")
	public Optional<OrderDetails>viewOrderById(@PathVariable("orderId") int orderId)
	{
	
		return orderDetailsServ.viewOrderById(orderId);
	}
	
	// 9 Update Delivery Status
	@PatchMapping("/orderdetails/{orderId}")
	public OrderDetails updateDeliveryStatus(@PathVariable("orderId") int orderId, @RequestBody String deliveryStatus)
	{
		LOGGER.info(
				"Updating delivery status of a particular order detail by patch mapping using OrderDetails Controller");
		return orderDetailsServ.updateDeliveryStatus(orderId, deliveryStatus);

	}
}
