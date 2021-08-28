package com.cg.bookstore.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.Address;
import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.dto.BookOrderDto;
import com.cg.bookstore.dto.BookOrderTotalDto;
import com.cg.bookstore.dto.BookOrderUpdateDto;
import com.cg.bookstore.entities.Customer;

import com.cg.bookstore.exception.BookOrderFoundException;
import com.cg.bookstore.service.IBookOrderService;

import lombok.var;

@RestController
public class BookOrderController {
	
	@Autowired
	IBookOrderService bookOrderService;
	final static Logger LOGGER = LogManager.getLogger(BookOrderController.class);
	
	/*
	 * Post mapping for adding new BookOrder in the system
	 * Created(201) status if successfully added and server error if unable to create
	 *
	*/
	
    @PostMapping("/BookOrder")
    public ResponseEntity<BookOrder> addBookOrder(@Valid @RequestBody BookOrderDto bookOrder) throws Exception{
    		LOGGER.info("Creating new book order using Post Mapping via BookOrder Controller");
    		BookOrder bo = bookOrderService.addBookOrder(bookOrder);
    		return new ResponseEntity<>(bo, HttpStatus.CREATED);
    	
    }
    /*
     * Delete Mapping to remove a Book Order based on given Id
     * 
     */
    @DeleteMapping(value = "/BookOrder/{id}")
    public ResponseEntity<Integer> deleteBookOrderById(@PathVariable int id) {
    		 LOGGER.info("Deleting a book order using Delete Mapping via BookOrder Controller");
        	 bookOrderService.cancelBookOrderById(id);
        	 return new ResponseEntity<>(id, HttpStatus.OK);	 
    }
    
	/*
	 * Get mapping for getting all Book Orders the system
	 * Status Ok if retrieved
	 *
	*/
	
	@GetMapping("/BookOrder/all")
	public ResponseEntity<List<BookOrder>> getAllBookOrders() {
		LOGGER.info("Fetching all book orders using Get Mapping via BookOrder Controller");
			List<BookOrder> bookOrders = bookOrderService.listAllBookOrders();
			return new ResponseEntity<>(bookOrders, HttpStatus.OK);

	}
	
	/*
	 * Get mapping for getting Total Amount of Book Order Customer Name wise from the system using GROUP BY ORDER BY
	 * Status Ok if retrieved
	 *
	*/
	
	@GetMapping("/BookOrder/getBookOrdersInAscBasedOnName/")
	public ResponseEntity<List<BookOrderTotalDto>> getTotalBookOrderByCustomer() {
		LOGGER.info("Get mapping for getting Total Amount of Book Order Customer Name wise from the system using GROUP BY ORDER BY via BookOrder Controller");
			List<BookOrderTotalDto> bookOrders = bookOrderService.viewTotalBookOrderByCustomer();
			return new ResponseEntity<>(bookOrders, HttpStatus.OK);
			
	}
	
	/*
	 * Get mapping for getting Total Amount of Book Order By a Customer ,Order total wise from the system using GROUP BY ORDER BY
	 * Status Ok if retrieved
	 *
	*/
	
	@GetMapping("/BookOrder/getBookOrdersInAscBasedOnTotal/")
	public ResponseEntity<List<BookOrderTotalDto>> getTotalBookOrderByCustomerByTotal() {
		LOGGER.info("Get mapping for fetching total amount by a customer via BookOrder Controller");
			List<BookOrderTotalDto> bookOrders = bookOrderService.viewTotalBookOrderByCustomerSortByTotal();
			return new ResponseEntity<>(bookOrders, HttpStatus.OK);
			
	}
	
	/*
	 * Get mapping to get a specific book order based on id
	 * status ok if retrieved
	 * NOT_FOUND if unable to find
	*/
   
	@GetMapping("/BookOrder/{id}")
	public ResponseEntity<BookOrder> getBookOrderById(@PathVariable("id") int id) {
		LOGGER.info("Fetching a particular book order based on id using Get Mapping via BookOrder Controller");
		Optional<BookOrder> optBookOrder = Optional.ofNullable(bookOrderService.getBookOrderById(id));

	    if (optBookOrder.isPresent()) {
	      return new ResponseEntity<>(optBookOrder.get(), HttpStatus.OK);
	    } else {
	    	LOGGER.error("No book order found");
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
	  }
	
	/*
	 * Get mapping to get a specific book order based on Customer id
	 * status ok if retrieved
	 * NOT_FOUND if unable to find
	*/
	
	@GetMapping("/BookOrder/getBookOrderByCustomerId/{id}")
	public ResponseEntity<List<BookOrder>> getBookOrderByCustomerId(@PathVariable("id") int id) {
		LOGGER.info("Fetching book order based on customer id using Get Mapping via BookOrder Controller");
		Optional<List<BookOrder>> optBookOrder = Optional.ofNullable(bookOrderService.viewBookOrderByCustomerId(id));

		    if (optBookOrder.get().size()!=0) {
		      
		    	 return new ResponseEntity<>(optBookOrder.get(), HttpStatus.OK);
		    } else {
		    	LOGGER.error("No book order found");
		    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
	}
	
	/*
	 * Get mapping to get a specific book order based on Customer Name
	 * status ok if retrieved
	 * NOT_FOUND if unable to find
	*/
	@GetMapping("/BookOrder/getBookOrderByCustomerName/{name}")
	public ResponseEntity<List<BookOrder>> getBookOrderByCustomerName(@PathVariable("name") String name) {
		LOGGER.info("Fetching book order based on customer name using Get Mapping via BookOrder Controller");
		Optional<List<BookOrder>> optBookOrder = Optional.ofNullable(bookOrderService.viewBookOrderByCustomerName(name));

		    if (optBookOrder.get().size()!=0) {
		      
		    	 return new ResponseEntity<>(optBookOrder.get(), HttpStatus.OK);
		    } else {
		    	LOGGER.error("No book order found");
		    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
	}
	
	/*
	 * Get mapping to get a specific book order based on City for shipping
	 * status ok if retrieved
	 * NOT_FOUND if unable to find
	*/
	@GetMapping("/BookOrder/getBookOrderByCity/{city}")
	public ResponseEntity<List<BookOrder>> getBookOrderByCity(@PathVariable("city") String city) {
		LOGGER.info("Fetching book order based on shipping city using Get Mapping via BookOrder Controller");
		Optional<List<BookOrder>> optBookOrder = Optional.ofNullable(bookOrderService.viewBookOrderByCity(city));

		    if (optBookOrder.get().size()!=0) {
		      
		    	 return new ResponseEntity<>(optBookOrder.get(), HttpStatus.OK);
		    } else {
		    	LOGGER.error("No book order found");
		    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
	 }
	
	/*
	 * Get mapping to get a specific book order based on Country for shipping
	 * status ok if retrieved
	 * NOT_FOUND if unable to find
	*/
	@GetMapping("/BookOrder/getBookOrderByCountry/{country}")
	public ResponseEntity<List<BookOrder>> getBookOrderByCountry(@PathVariable("country") String country) {
		LOGGER.info("Fetching book order based on shipping country using Get Mapping via BookOrder Controller");
		Optional<List<BookOrder>> optBookOrder = Optional.ofNullable(bookOrderService.viewBookOrderByCountry(country));

		    if (optBookOrder.get().size()!=0) {
		      
		    	 return new ResponseEntity<>(optBookOrder.get(), HttpStatus.OK);
		    } else {
		    	LOGGER.error("No book order found");
		    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
	 }
	
	/*
	 * Get mapping to get a specific book order based on Pincode for shipping
	 * status ok if retrieved
	 * NOT_FOUND if unable to find
	*/
	@GetMapping("/BookOrder/getBookOrderByPincode/{pincode}")
	public ResponseEntity<List<BookOrder>> getBookOrderByPincode(@PathVariable("pincode") String pincode) {
		
		Optional<List<BookOrder>> optBookOrder = Optional.ofNullable(bookOrderService.viewBookOrderByPincode(pincode));

		    if (optBookOrder.get().size()!=0) {
		      
		    	 return new ResponseEntity<>(optBookOrder.get(), HttpStatus.OK);
		    } else {
		    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
	 }
	
	/*
	 * Put Mapping for updating a Book Order
	 * ACCEPTED if processed successfully 
	*/
	@PutMapping("/BookOrder/{id}")
	public ResponseEntity<BookOrder> updateBookOrder(@PathVariable("id") int id, @RequestBody BookOrderUpdateDto bookOrderDto)
	{
		LOGGER.info("Fetching book order based on shipping country using Get Mapping via BookOrder Controller");
		bookOrderService.updateBookOrder(id, bookOrderDto);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
}
