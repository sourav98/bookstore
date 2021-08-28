package com.cg.bookstore.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Address;
import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.dto.BookOrderDto;
import com.cg.bookstore.dto.BookOrderTotalDto;
import com.cg.bookstore.dto.BookOrderUpdateDto;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.exception.AddressNotFoundException;
import com.cg.bookstore.exception.BookOrderFoundException;
import com.cg.bookstore.exception.BookOrderNotFoundException;
import com.cg.bookstore.repository.IAddressRepository;
import com.cg.bookstore.repository.IBookOrderRepository;
import com.cg.bookstore.repository.ICustomerRepository;



@Service
public class BookOrderServiceImpl implements IBookOrderService {
	
	final static Logger LOGGER = LogManager.getLogger(BookOrderServiceImpl.class);
	
	@Autowired 
	IBookOrderRepository bookOrderRepo;
	
	@Autowired
	ICustomerRepository customerRepo;
	
	@Autowired
	IAddressRepository addressRepo;
	
	/*@Autowired
	BookOrder bookOrder;
	*/

	@Override
	public List<BookOrder> listAllBookOrders() {
		LOGGER.info("Fetch all book orders using BookOrder Service Implementation");
		return bookOrderRepo.findAll();
	}
    
	/*
	 * Getting BOOK ORDER by ID 
	 *throws Exception BookOrderNotFoundException if Book Order is not found
	*/
	@Override
	public BookOrder getBookOrderById(int id) {
		LOGGER.info("Fetch a particular book order based on id using BookOrder Service Implementation");
		Optional<BookOrder> opt = bookOrderRepo.findById(id);
		if(!opt.isPresent()) {
			LOGGER.error("No BookOrder Found with given book id");
			throw new BookOrderNotFoundException(" Book Order with id : "+ id +" not found !!");
		}
		return opt.get();
	}


	

	@Override
	public void cancelBookOrderById(int id) {
		LOGGER.info("Deleting a particular book order based on id using BookOrder Service Implementation");
		Optional<BookOrder> opt = bookOrderRepo.findById(id);
		if(!opt.isPresent()) {
			LOGGER.error("No book order found");
			throw new BookOrderNotFoundException(" Book Order with id : "+ id +" not found !!");
		}
		bookOrderRepo.delete(opt.get());
	}

	

	@Override
	public List<BookOrder> viewBookOrderByCity(String city) {
		LOGGER.info("Fetch a particular book order based on city using BookOrder Service Implementation");
		Optional<List<BookOrder>> opt = Optional.ofNullable(bookOrderRepo.viewBookOrderByCity(city));
		if( opt.get().size() == 0) {
			LOGGER.error("No BookOrder found from given city");
			throw new BookOrderNotFoundException(" Book Order/s with city : "+ city +" not found !!");
		}
		return opt.get();
		
	}

	@Override
	public List<BookOrder> viewBookOrderByPincode(String pincode) {
		LOGGER.info("Fetch a particular book order based on pincode using BookOrder Service Implementation");
		Optional<List<BookOrder>> opt = Optional.ofNullable(bookOrderRepo.viewBookOrderByPincode(pincode));
		if(opt.get().size() == 0) {
			LOGGER.error("No BookOrder found from given pincode");
			throw new BookOrderNotFoundException(" Book Order/s with pincode : "+ pincode +" not found !!");
		}
		return opt.get();
		
	}

	@Override
	public List<BookOrder> viewBookOrderByCountry(String country) {
		LOGGER.info("Fetch a particular book order based on country using BookOrder Service Implementation");
		Optional<List<BookOrder>> opt = Optional.ofNullable(bookOrderRepo.viewBookOrderByCountry(country));
		if(opt.get().size() == 0) {
			LOGGER.error("No BookOrder found from given pincode");
			throw new BookOrderNotFoundException(" Book Order/s for country : "+ country +" not found !!");
		}
		return opt.get();
		
	}

	@Override
	public List<BookOrder> viewBookOrderByCustomerId(int id) {
		LOGGER.info("Fetch book orders based on customer id using BookOrder Service Implementation");
		Optional<List<BookOrder>> opt = Optional.ofNullable(bookOrderRepo.viewBookOrderByCustomerId(id));
		if(opt.get().size() == 0) {
			LOGGER.error("No BookOrder found from given pincode");
			throw new BookOrderNotFoundException(" Book Order/s for customer with id : "+ id +" not found !!");
		}
		return opt.get();
		
	}

	@Override
	public List<BookOrder> viewBookOrderByCustomerName(String name) {
		LOGGER.info("Fetch all book orders based on customer name using BookOrder Service Implementation");
		Optional<List<BookOrder>> opt = Optional.ofNullable(bookOrderRepo.viewBookOrderByCustomerName(name));
		if(opt.get().size() == 0) {
			LOGGER.error("No BookOrders found for given customer");
			throw new BookOrderNotFoundException(" Book Order/s for customer with name : "+ name +" not found !!");
		}
		return opt.get();
		
	}
    
	
	@Override
	public List<BookOrderTotalDto> viewTotalBookOrderByCustomer() {
		LOGGER.info("Fetch total book order based on customer name using BookOrder Service Implementation");
		return bookOrderRepo.viewTotalBookOrderByCustomer();
	}
    

	@Override
	public BookOrder addBookOrder(BookOrderDto bookOrderDto) {
		LOGGER.info("Add a book order using BookOrder Service Implementation");
		Optional<Address> address = addressRepo.findById(bookOrderDto.getAddressId());
		Optional<Customer> customer = customerRepo.findById(bookOrderDto.getCustomerId());
		
		if(address.isPresent() && customer.isPresent()) {
			BookOrder bookOrder = new BookOrder();
			
			bookOrder.setCustomer(customer.get());
			bookOrder.setOrderTotal(bookOrderDto.getOrderTotal());
			bookOrder.setPaymentMethod(bookOrderDto.getPaymentMethod());
			bookOrder.setRecipientName(bookOrderDto.getRecipientName());
			bookOrder.setRecipientPhone(bookOrderDto.getRecipientPhone());
			bookOrder.setShippingAddress(address.get());
			bookOrder.setStatus(bookOrderDto.getStatus());
			
			bookOrderRepo.save(bookOrder);
			return bookOrder;
			
		}
		else{
			LOGGER.error("BookOrder can't be added");
			throw new AddressNotFoundException("Book Order can't be added");
		}
		
	}

	
	
	@Override
	public BookOrder updateBookOrder(int bookOrderId ,BookOrderUpdateDto bookOrderDto) {
		LOGGER.info("Updating a particular book order using BookOrder Service Implementation");
		Optional<BookOrder> response = bookOrderRepo.findById(bookOrderId);
		Optional<Address> address = addressRepo.findById(bookOrderDto.getAddressId());
		
		
		if(!response.isPresent()) {
			LOGGER.error("No BookOrder found");
			throw new BookOrderNotFoundException("Book order for given Id " + bookOrderId +" not found");
		}
		
		BookOrder bookOrder = response.get();
		bookOrder.setOrderTotal(bookOrderDto.getOrderTotal());
		bookOrder.setPaymentMethod(bookOrderDto.getPaymentMethod());
		bookOrder.setRecipientName(bookOrderDto.getRecipientName());
		bookOrder.setRecipientPhone(bookOrderDto.getRecipientPhone());
		bookOrder.setShippingAddress(address.get());
		bookOrder.setStatus(bookOrderDto.getStatus());
		bookOrderRepo.save(bookOrder);
		return bookOrder;
		
	}

	@Override
	public List<BookOrderTotalDto> viewTotalBookOrderByCustomerSortByTotal() {
		LOGGER.info("Fetching total book order by customer using BookOrder Service Implementation");
		return bookOrderRepo.viewTotalBookOrderByCustomerSortByTotal();
	}

	@Override
	public BookOrder updateTotalForId(int id, Double total) {
		/*
		 * 	Optional<Address> opt = addRepo.findById(addressId);
		if(!opt.isPresent()) {
			throw new AddressNotFoundException("Address not found with the given id: "+addressId);  //exception handled in AddressExceptionHandler---> handleAddressNotFoundException
		}
		Address dbAdd = opt.get();
		dbAdd.setCity(city);
		addRepo.save(dbAdd);
		return dbAdd;*/
		LOGGER.info("Updating total for id using BookOrder Service Implementation");
		Optional<BookOrder> opt = bookOrderRepo.findById(id);
		if( !opt.isPresent()) {
			LOGGER.error("No BookOrder found");
			throw new BookOrderNotFoundException(" Book Order Not Found ");
		}
		BookOrder dbBookOrder = opt.get();
		dbBookOrder.setOrderTotal(total);
		bookOrderRepo.save(dbBookOrder);
		return dbBookOrder;
	}
	
	
}
