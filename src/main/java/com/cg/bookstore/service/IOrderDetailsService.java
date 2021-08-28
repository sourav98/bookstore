package com.cg.bookstore.service;

import java.util.List;
import java.util.Optional;

import com.cg.bookstore.dto.OrderDetailsBookDto;
import com.cg.bookstore.dto.OrderDetailsCustomerDto;
import com.cg.bookstore.dto.OrderDetailsDto;
import com.cg.bookstore.dto.OrderDetailsUpdateDto;
import com.cg.bookstore.entities.OrderDetails;

public interface IOrderDetailsService
{
//	List<OrderDetails> viewOrdersForAdmin(int userId); // view for admin 
	
	List<OrderDetails> listAllOrders(); // 1 List All Orders // DONE

	List<OrderDetailsCustomerDto> listOrderByCustomer(int customerId); // 2 List Order By Customer // DONE

	OrderDetails updateDeliveryStatus (int orderId, String deliveryStatus); // Update Order Delivery Status

	void cancelOrder(int orderDetailId); // 5 Cancel Order // DONE

	OrderDetails addOrder(OrderDetailsDto orderDetailsDto); // 6 Add Order // DONE

	OrderDetails updateOrder(int orderId, OrderDetailsUpdateDto orderDetailsUpdateDto); // 7 Update Order // DONE

	List<OrderDetailsBookDto> viewOrderByBook(int bookId); // 8 View Order By Book // DONE
	
	Optional<OrderDetails>  viewOrderById(int orderId);
}
