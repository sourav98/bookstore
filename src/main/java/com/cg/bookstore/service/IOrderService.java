package com.cg.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.OrderDetails;

@Service
public interface IOrderService {

	public List<OrderDetails> listAllOrders();//done
	public List<OrderDetails> listOrderByCustomer(Customer cs);// select book order id where customer_id = iven customer id then return those order details with given book order id
	public OrderDetails viewOrderForCustomer(OrderDetails od);
	public OrderDetails viewOrderForAdmin(OrderDetails od);
	public OrderDetails cancelOrder(Integer id);//done
	public OrderDetails addOrder(OrderDetails od);//done
	public OrderDetails updateOrder(OrderDetails od);//done
	public List<OrderDetails> viewOrderByBook(Book book);
	public List<Book> listBestSellingBook();

}
