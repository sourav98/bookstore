package com.cg.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.OrderDetails;



@Repository
public interface IOrderRepository extends JpaRepository<OrderDetails,Integer >{

	//public List<OrderDetails> listAllOrders();                    \\done
	/*public List<OrderDetails> listOrderByCustomer(Customer cs);
	public OrderDetails viewOrderForCustomer(OrderDetails od);
	public OrderDetails viewOrderForAdmin(OrderDetails od);
	public OrderDetails cancelOrder(OrderDetails od);                \\done
	public OrderDetails addOrder(OrderDetails od);                   \\done
	public OrderDetails updateOrder(OrderDetails od);                \\done
	public List<OrderDetails> viewOrderByBook(Book book);
	public List<Book> listBestSellingBook();
*/
	//public OrderDetails updateOrder(OrderDetails od);
	// select book order id where customer_id = iven customer id then return those order details with given book order id
	@Query(" SELECT o FROM OrderDetails o where o.bookOrder IN ( select orderId from BookOrder b where b.customer = :cs)")
	List<OrderDetails> listOrderByCustomer(@Param("cs") Customer cs );
}
