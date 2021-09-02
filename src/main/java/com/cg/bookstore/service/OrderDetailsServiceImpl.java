package com.cg.bookstore.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.dto.OrderDetailsBookDto;
import com.cg.bookstore.dto.OrderDetailsCustomerDto;
import com.cg.bookstore.dto.OrderDetailsDto;
import com.cg.bookstore.dto.OrderDetailsUpdateDto;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.BookOrder;
//import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.OrderDetails;
import com.cg.bookstore.entities.User;
import com.cg.bookstore.exception.OrderDetailsFoundException;
import com.cg.bookstore.exception.OrderDetailsNotFoundException;
import com.cg.bookstore.repository.IBookOrderRepository;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.ICustomerRepository;
import com.cg.bookstore.repository.IOrderDetailsRepository;
import com.cg.bookstore.repository.IUserRepository;

@Service
public class OrderDetailsServiceImpl implements IOrderDetailsService
{

	final static Logger LOGGER = LogManager.getLogger(OrderDetailsServiceImpl.class);
	@Autowired
	IOrderDetailsRepository orderDetailsRepo;
	@Autowired
	IBookOrderRepository bookOrderRepo;
	@Autowired
	IBookRepository bookRepo;
	@Autowired
	ICustomerRepository customerRepo;
	
	@Autowired
	IUserRepository userRepo;
	
	// To list all orders placed // with admin role check
	
//	@Override
//	public List<OrderDetails> viewOrdersForAdmin(int userId) throws OrderDetailsNotFoundException
//	{
//		LOGGER.info("Fetching all orders using OrderDetails Service Implementation");
//		Optional<User> user = userRepo.findById(userId);
//		if(user.isPresent()) {
//		if(user.get().getRole().equals("admin"))
//		{
//			List<OrderDetails> opt = (List<OrderDetails>) orderDetailsRepo.findAll();
//			if (opt.isEmpty())
//			{
//				LOGGER.error("No orders present");
//				throw new OrderDetailsNotFoundException("No Orders exist");
//			}
//
//			return opt;
//		}
//		else {
//			throw new OrderDetailsNotFoundException("User Not Admin");
//		}
//		
//	} throw new OrderDetailsNotFoundException("Invalid User ID");
//		
//	}
	
	// To list all orders placed // without admin role check
	
	@Override
	public List<OrderDetails> listAllOrders()
	{
		LOGGER.info("Fetching all orders using OrderDetails Service Implementation");
		List<OrderDetails> opt = (List<OrderDetails>) orderDetailsRepo.findAll();
		if (opt.isEmpty())
		{
			LOGGER.error("No orders present");
			throw new OrderDetailsNotFoundException("No Orders exist");
		}

		return opt;
	}

//to list all orders placed by a particular customer

	@Override
	public List<OrderDetailsCustomerDto> listOrderByCustomer(int customerId)
	{
		LOGGER.info("Fetching all orders by customer name using OrderDetails Service Implementation");
		List<OrderDetailsCustomerDto> opt = new ArrayList<>();
		orderDetailsRepo.listOrderByCustomer(customerId).forEach(opt::add);
		if (opt.isEmpty())
		{
			LOGGER.error("No order present");
			throw new OrderDetailsNotFoundException("You Haven't made any orders");
		}
		return opt;
	}

// Cancel an order that is placed

	@Override
	public void cancelOrder(int orderId)
	{
		LOGGER.info("Deleting particular order using OrderDetails Service Implementation");
		Optional<OrderDetails> opt = orderDetailsRepo.findById(orderId);
		if (opt.isPresent())
		{
			Optional<Book> book = bookRepo.findById(opt.get().getBook().getBookId());
			book.get().setStock(book.get().getStock()+opt.get().getQuantity());
			orderDetailsRepo.deleteById(orderId);
		} else
		{
			LOGGER.error("No order present");
			throw new OrderDetailsNotFoundException("Order Id is not found");
		}

	}

	// Add an order

	@Override
	public OrderDetails addOrder(OrderDetailsDto orderDetailsDto)
	{
		LOGGER.info("Adding new order using OrderDetails Service Implementation");
		// if book and book order ( which includes customer) is present
		Optional<Book> book = bookRepo.findById(orderDetailsDto.getBookId());
		Optional<BookOrder> bookOrder = bookOrderRepo.findById(orderDetailsDto.getOrderId());

		if (book.isPresent())
		{
			if (book.get().getStock() > 0)
			{
				if (bookOrder.isPresent())
				{
					OrderDetails orderDetails = new OrderDetails();
					orderDetails.setBook(book.get());
					orderDetails.setBookOrder(bookOrder.get());
					orderDetails.setQuantity(orderDetailsDto.getQuantity());
					int bookQuantity = orderDetailsDto.getQuantity();
					book.get().setStock((book.get().getStock()) - bookQuantity);
					int bookPrice = Integer.parseInt(book.get().getPrice());
					orderDetails.setOrderTotal(bookQuantity * bookPrice);
					bookOrderRepo.updateOrderTotal(orderDetailsDto.getOrderId(), orderDetails.getOrderTotal());
					orderDetailsRepo.save(orderDetails);

					return orderDetails;
				} else
				{
					LOGGER.error("Order details not found");
					throw new OrderDetailsNotFoundException("Order Id is not found");
				}
			} else
				throw new OrderDetailsNotFoundException("Stock unavailable");

		}
		throw new OrderDetailsNotFoundException("Book id is is not found");
	}

	// update an order

	@Override
	public OrderDetails updateOrder(int orderId, OrderDetailsUpdateDto orderDetailsUpdateDto)
	{
		LOGGER.info("Updating order using OrderDetails Service Impl");
		Optional<OrderDetails> od = orderDetailsRepo.findById(orderId);

		if (od.isPresent())

		{
			OrderDetails orderDetails = od.get();
			//orderDetails.setQuantity(orderDetailsUpdateDto.getQuantity());
			//int bookQuantity = orderDetailsUpdateDto.getQuantity();
			//int bookPrice = Integer.parseInt(od.get().getBook().getPrice());
			//orderDetails.setOrderTotal(bookQuantity * bookPrice);
			//orderDetails.getBook().setStock(orderDetails.getBook().getStock() - bookQuantity);
			orderDetails.setDeliveryStatus(orderDetailsUpdateDto.getDeliveryStatus());			
			orderDetailsRepo.save(orderDetails);
			
			

			return orderDetails;
		}
		throw new OrderDetailsNotFoundException("Order id is is not found");

	}

	// view the orders based on book

	@Override
	public List<OrderDetailsBookDto> viewOrderByBook(int bookId)
	{
		LOGGER.info("Fetch order by book using OrderDetails Service Implementation");
		List<OrderDetailsBookDto> opt = orderDetailsRepo.viewOrderByBook(bookId);
		if (opt.isEmpty())
		{
			LOGGER.error("No order found");
			throw new OrderDetailsNotFoundException("Book Id is not found");
		}
		return orderDetailsRepo.viewOrderByBook(bookId);
	}
	
	@Override
	public Optional<OrderDetails> viewOrderById(int orderId)
	{
		return orderDetailsRepo.findById(orderId);
	}

// update the default delivery status of the order 

	@Override
	public OrderDetails updateDeliveryStatus(int orderId, String deliveryStatus)
	{
		LOGGER.info("Updating delivery status of an order using OrderDetails Service Implementation");
		Optional<OrderDetails> opt = orderDetailsRepo.findById(orderId);
		OrderDetails od;
		if (opt.isPresent())
		{
			od = opt.get();
			od.setDeliveryStatus(deliveryStatus);
			orderDetailsRepo.save(od);
		} else
		{
			LOGGER.error("No order found");
			throw new OrderDetailsNotFoundException("Order is not found");
		}
		return od;
	}

}