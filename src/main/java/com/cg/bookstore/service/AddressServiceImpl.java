package com.cg.bookstore.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.repository.IAddressRepository;
import com.cg.bookstore.dto.AddressCustomerDto;
import com.cg.bookstore.dto.AddressStatusDto;
import com.cg.bookstore.entities.Address;
import com.cg.bookstore.exception.AddressFoundException;
import com.cg.bookstore.exception.AddressNotFoundException;

@Service
public class AddressServiceImpl implements IAddressService {
	
	final static Logger LOGGER = LogManager.getLogger(AddressServiceImpl.class);
	
	@Autowired
	IAddressRepository addRepo;
	
	
	//add address
	@Override
	public Address addAddress(Address address) {
		LOGGER.info("Adding address using Address Service implementation");
		Optional<Address> opt = addRepo.findById(address.getAddressId());
		if(opt.isPresent()) {
			LOGGER.error("Address already present with given id");
			throw new AddressFoundException("Address with given id is already present in database"); //exception handled in AddressExceptionHandler---> handleAddressFoundException
		}
		return addRepo.save(address);  //validation exceptions handled in AddressExceptionHandler--->handleException
	}
	
	
	//fetch all addresses
	@Override
	public List<Address> getAllAddresses() {
		LOGGER.info("Fetching all addresses using Address Service Implementation");
		List<Address> allAdd = addRepo.findAll();
		if(allAdd.isEmpty()) {
			LOGGER.error("No addresses present");
			throw new AddressNotFoundException("No addressess added yet");  //exception handled in AddressExceptionHandler---> handleAddressNotFoundException
		}
		return allAdd;
	}
	
	
	//get address by id
	@Override
	public Address getById(int addressId) {
		LOGGER.info("Fetching a particular address using Address Service Implementation");
		Optional<Address> opt = addRepo.findById(addressId);
		if(!opt.isPresent()) {
			LOGGER.error("No addresses present with given id");
			throw new AddressNotFoundException("Address not found with the given id: "+addressId);  //exception handled in AddressExceptionHandler---> handleAddressNotFoundException
		}
		return opt.get();
	}
	
	
	//delete by address id
	@Override
	public Address deleteById(int addressId) {
		LOGGER.info("Deleting a particular address by id using Address Service Implementation");
		Optional<Address> opt = addRepo.findById(addressId);
		if(!opt.isPresent()) {
			throw new AddressNotFoundException("Address not found with the given id: "+addressId);  //exception handled in AddressExceptionHandler---> handleAddressNotFoundException
		}
		
		addRepo.deleteById(addressId);
		return opt.get();
	}
	
	
	//patch city by address id
	@Override
	public Address updateCityById(int addressId, String city) {
		LOGGER.info("Updating city of a particular address by id using Address Service Implementation");
		Optional<Address> opt = addRepo.findById(addressId);
		if(!opt.isPresent()) {
			LOGGER.error("No address with given id");
			throw new AddressNotFoundException("Address not found with the given id: "+addressId);  //exception handled in AddressExceptionHandler---> handleAddressNotFoundException
		}
		Address dbAdd = opt.get();
		dbAdd.setCity(city);
		addRepo.save(dbAdd);
		return dbAdd;
	}
	
	
	
	//patch country by address id
	@Override
	public Address updateCountryById(int addressId, String country) {
		LOGGER.info("Updating country of a particular address by id using Address Service Implementation");
		Optional<Address> opt = addRepo.findById(addressId);
		if(!opt.isPresent()) {
			LOGGER.error("No address present with given id");
			throw new AddressNotFoundException("Address not found with the given id: "+addressId);  //exception handled in AddressExceptionHandler---> handleAddressNotFoundException
		}
		Address dbAdd = opt.get();
		dbAdd.setCountry(country);
		addRepo.save(dbAdd);
		return dbAdd;
	}
	
	
	//patch pincode by address id
	@Override
	public Address updatePincodeById(int addressId, String pincode) {
		LOGGER.info("Updating pincode of a particular address by id using Address Service Implementation");
		Optional<Address> opt = addRepo.findById(addressId);
		if(!opt.isPresent()) {
			LOGGER.error("No address present with given id");
			throw new AddressNotFoundException("Address not found with the given id: "+addressId);  //exception handled in AddressExceptionHandler---> handleAddressNotFoundException
		}
		Address dbAdd = opt.get();
		dbAdd.setPincode(pincode);
		addRepo.save(dbAdd);
		return dbAdd;
	}
	
	
	//put updated address by address id
	@Override
	public Address updateAddress(Address address) {
		LOGGER.info("Updating a particular address using Address Service Implementation");
		Optional<Address> opt = addRepo.findById(address.getAddressId());
		if(!opt.isPresent()) {
			LOGGER.error("No address found");
			throw new AddressNotFoundException("Address not found with the given id: "+address.getAddressId());  //exception handled in AddressExceptionHandler---> handleAddressNotFoundException
		}
		Address dbAdd = opt.get();
		dbAdd.setAddress(address.getAddress());
		dbAdd.setCity(address.getCity());
		dbAdd.setCountry(address.getCountry());
		dbAdd.setPincode(address.getPincode());
		addRepo.save(dbAdd);
		return dbAdd;
	}


	@Override
	public List<Address> findByCity(String city) {
		LOGGER.info("Fetching all addresses by city using Address Service Implementation");
		List<Address> allAdd=addRepo.findByCity(city);
		if(allAdd.isEmpty()) {
			LOGGER.error("No addresses found with given city");
			throw new AddressNotFoundException("No addressess with given city name");  //exception handled in AddressExceptionHandler---> handleAddressNotFoundException
		}
		return allAdd;
	}


	@Override
	public ArrayList<Address> getAddressByPincode(String pincode) {
		LOGGER.info("Fetching all addresses by pincode using Address Service Implementation");
		ArrayList<Address> allAdd = addRepo.getAddressByPincode(pincode);
		if(allAdd.isEmpty()) {
			LOGGER.error("No addresses found with given pincode");
			throw new AddressNotFoundException("No addressess with given pincode");  //exception handled in AddressExceptionHandler---> handleAddressNotFoundException
		}
		return allAdd;

	}


	@Override
	public List<AddressStatusDto> getAddressStatus()
	{
		LOGGER.info("Fetching all addresses status using Address Service Implementation");
		List<AddressStatusDto> response = addRepo.getAddressStatus();
		if (response.isEmpty())
		{
			LOGGER.error("No addresses found");
			throw new AddressNotFoundException("No orders yet!");
		}
		return response;
	}

	@Override
	public List<AddressStatusDto> getAddressStatusByCountry()
	{
		LOGGER.info("Fetching all addresses status using Address Service Implementation");
		List<AddressStatusDto> response = addRepo.getAddressStatusByCountry();
		if (response.isEmpty())
		{
			LOGGER.error("No addresses found");
			throw new AddressNotFoundException("No orders yet!");
		}
		return response;
	}

	@Override
	public List<AddressStatusDto> getAddressStatusBySpecificCity(String city)
	{
		LOGGER.info("Fetching all addresses status using City");
		List<AddressStatusDto> response = addRepo.getAddressStatusBySpecificCity(city);
		if (response.isEmpty())
		{
			LOGGER.error("No addresses found from given city");
			throw new AddressNotFoundException("No orders from city: " + city + " yet!");
		}
		return response;
	}

	@Override
	public List<AddressStatusDto> getAddressStatusBySpecificCountry(String country)
	{
		LOGGER.info("Fetching all addresses status using Country");
		List<AddressStatusDto> response = addRepo.getAddressStatusBySpecificCountry(country);
		if (response.isEmpty())
		{
			LOGGER.error("No addresses found from given Country");
			throw new AddressNotFoundException("No orders from country: " + country + " yet!");
		}
		return response;
	}


	@Override
	public List<AddressCustomerDto> getAddressCustomer() {
		List<AddressCustomerDto> response = addRepo.getAddressCustomer();
		return response;
	}

}
