package com.cg.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.bookstore.dto.OrderDetailsBookDto;
import com.cg.bookstore.dto.OrderDetailsCustomerDto;
import com.cg.bookstore.entities.OrderDetails;

public interface IOrderDetailsRepository extends JpaRepository<OrderDetails, Integer>
{
	// select * from order_details o where o.order_id_fk IN ( select b.order_id from
	// book_order b where b.customer_id_fk=:customer_id_fk);
	
	// To display all orders by a particular customer
	
	@Query("select new com.cg.bookstore.dto.OrderDetailsCustomerDto(b.bookId,b.title,b.price,od.quantity, c.fullName,c.customerId,od.orderTotal,od.deliveryStatus) from OrderDetails od inner join Book b on od.book.bookId=b.bookId inner join BookOrder bo on od.bookOrder.orderId=bo.orderId inner join Customer c on bo.customer.customerId=c.customerId where bo.customer.customerId=:customerId ")
	List<OrderDetailsCustomerDto> listOrderByCustomer(@Param("customerId") int customerId);

	// To display orders based on books
	
	@Query("select new com.cg.bookstore.dto.OrderDetailsBookDto(b.title,b.price,od.quantity) from OrderDetails od inner join Book b on od.book.bookId=b.bookId where od.book.bookId=:bookId")
	List<OrderDetailsBookDto> viewOrderByBook(@Param("bookId") int bookId);
}









/*
 * @Query("SELECT new com.project.bookmanagement.dto.OrderDetailsCustomerDto() from OrderDetails od where od.orderDetailsId IN (SELECT b.orderId from BookOrder b where b.customer.customerId=:customerId)"
 * ) List<OrderDetailsCustomerDto> listOrderByCustomer(@Param("customerId") int
 * customerId );
 * 
 * @Query(value ="", nativeQuery=true) List<OrderDetails>
 * listOrderByCustomer(@Param("customer_id_fk") int customer_id_fk );
 * 
 * 
 * @Query("SELECT o from order_details o where o.book_order IN (select order_id from book_order b where b.customer=:cs)"
 * ) List<OrderDetails> listOrderByCustomer(@Param("cs") Customer cs);
 * 
 * @Query(value
 * ="select * from order_details od where order_details_id IN(select order_id from book_order where customer_id_fk=:customerId);"
 * ,nativeQuery=true) List<OrderDetails>
 * listOrderByCustomer(@Param("customerId") int customerId );
 * 
 */
