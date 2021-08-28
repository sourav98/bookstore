package com.cg.bookstore.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.dto.BookOrderTotalDto;
import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.entities.Customer;


@Repository
public interface IBookOrderRepository extends JpaRepository<BookOrder, Integer>{
 
 
	@Query(" SELECT o FROM BookOrder o where o.customer.customerId = :cs")
	List<BookOrder> viewBookOrderByCustomerId(@Param("cs") int cs );
	
	@Query(" SELECT o FROM BookOrder o where o.shippingAddress.city = :city")
	List<BookOrder> viewBookOrderByCity(@Param("city") String city );
	
	@Query(" SELECT o FROM BookOrder o where o.shippingAddress.country = :country")
	List<BookOrder> viewBookOrderByCountry(@Param("country") String country );
	 
	@Query(" SELECT o FROM BookOrder o where o.shippingAddress.pincode = :pincode")
	List<BookOrder> viewBookOrderByPincode(@Param("pincode") String city );
	
	@Query(" SELECT o FROM BookOrder o where o.customer.fullName = :name")
	List<BookOrder> viewBookOrderByCustomerName(@Param("name") String name );
	
	@Transactional
	@Modifying
	@Query( "UPDATE BookOrder o SET o.orderTotal = :total"+" WHERE o.orderId = :id  ")
	void updateOrderTotal(@Param("id") int id, @Param("total") Double total );
	
	//sort on basis odf customer name, total order 
	@Query(value = " SELECT new com.cg.bookstore.dto.BookOrderTotalDto(  sum( o.orderTotal) ,o.customer.fullName ) FROM BookOrder o GROUP BY o.customer.fullName ORDER BY o.customer.fullName ASC ")
	List<BookOrderTotalDto> viewTotalBookOrderByCustomer();
	//for sorting based on order total 
	@Query(" SELECT new com.cg.bookstore.dto.BookOrderTotalDto(  sum( o.orderTotal) ,o.customer.fullName ) FROM BookOrder o GROUP BY o.customer.fullName ORDER BY sum(o.orderTotal) ASC ")
	List<BookOrderTotalDto> viewTotalBookOrderByCustomerSortByTotal();
}
