package com.cg.bookstore.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.dto.AddressStatusDto;
import com.cg.bookstore.entities.Address;
import com.cg.bookstore.service.IAddressService;

@RestController
public class AddressController
{

	final static Logger LOGGER = LogManager.getLogger(AddressController.class);
	@Autowired
	IAddressService addServ;

	@PostMapping("/address")
	public ResponseEntity<Address> addAddress(@Valid @RequestBody Address address)
	{
		LOGGER.info("Adding address via post mapping using Address Controller");
		return new ResponseEntity<Address>(addServ.addAddress(address), HttpStatus.OK);
	}

	@GetMapping("/address/all")
	public ResponseEntity<List<Address>> getAllAddress()
	{
		LOGGER.info("Fetching all address via get mapping using Address Controller");
		return new ResponseEntity<List<Address>>(addServ.getAllAddresses(), HttpStatus.OK);
	}

	@GetMapping("/address/id/{id}")
	public ResponseEntity<Address> getById(@PathVariable("id") int addId)
	{
		LOGGER.info("Fetching particular address via get mapping using Address Controller");
		return new ResponseEntity<Address>(addServ.getById(addId), HttpStatus.OK);
	}

	@DeleteMapping("/address/delete/{id}")
	public ResponseEntity<Address> deleteAddById(@PathVariable("id") int addId)
	{
		LOGGER.info("Deleting particular address via delete mapping using Address Controller");
		return new ResponseEntity<Address>(addServ.deleteById(addId), HttpStatus.OK);
	}

	@PatchMapping("/address/city/{id}")
	public ResponseEntity<Address> updateCityById(@PathVariable("id") int id, @Valid @RequestBody String city)
	{
		LOGGER.info("Updating city of a particular address via patch mapping using Address Controller");
		return new ResponseEntity<Address>(addServ.updateCityById(id, city), HttpStatus.OK);
	}

	@PatchMapping("/address/country/{id}")
	public ResponseEntity<Address> updateCountryById(@PathVariable("id") int id, @Valid @RequestBody String country)
	{
		LOGGER.info("Updating country of a particular address via patch mapping using Address Controller");
		return new ResponseEntity<Address>(addServ.updateCountryById(id, country), HttpStatus.OK);
	}

	@PatchMapping("/address/pincode/{id}")
	public ResponseEntity<Address> updatePincodeById(@PathVariable("id") int id, @Valid @RequestBody String pincode)
	{
		LOGGER.info("Updating pincode of particular address via patch mapping using Address Controller");
		return new ResponseEntity<Address>(addServ.updatePincodeById(id, pincode), HttpStatus.OK);
	}

	@PutMapping("/address")
	public ResponseEntity<Address> updateAddress(@Valid @RequestBody Address address)
	{
		LOGGER.info("Updating address via put mapping using Address Controller");
		return new ResponseEntity<Address>(addServ.updateAddress(address), HttpStatus.OK);
	}

	@GetMapping("/address/city/{city}")
	public ResponseEntity<List<Address>> getAddressByCity(@PathVariable("city") String city)
	{
		LOGGER.info("Getting order details based on City");
		Optional<List<Address>> opt = Optional.ofNullable(addServ.findByCity(city));
		if (opt.get().size() != 0)
		{
			return new ResponseEntity<>(opt.get(), HttpStatus.OK);
		} else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/address/pincode/{pincode}")
	public ResponseEntity<ArrayList<Address>> getAddressByPincode(@PathVariable("pincode") String pincode)
	{
		LOGGER.info("Getting order details based on Pincode");
		Optional<ArrayList<Address>> opt = Optional.ofNullable(addServ.getAddressByPincode(pincode));
		if (opt.get().size() != 0)
		{
			return new ResponseEntity<>(opt.get(), HttpStatus.OK);
		} else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/address/status/city")
	public List<AddressStatusDto> getAddressStatus()
	{
		LOGGER.info("Getting Address status");
		return addServ.getAddressStatus();
	}

	@GetMapping("/address/status/country")
	public List<AddressStatusDto> getAddressStatusByCountry()
	{
		LOGGER.info("Getting Address status by Country");
		return addServ.getAddressStatusByCountry();
	}

	@GetMapping("/address/status/city/{city}")
	public ResponseEntity<List<AddressStatusDto>> getAddressStatusBySpecificCity(@PathVariable("city") String city)
	{
		LOGGER.info("Getting Address by City");
		return new ResponseEntity<>(addServ.getAddressStatusBySpecificCity(city), HttpStatus.OK);
	}

	@GetMapping("/address/status/country/{country}")
	public ResponseEntity<List<AddressStatusDto>> getAddressStatusBySpecificCountry(
			@PathVariable("country") String country)
	{
		LOGGER.info("Getting Address by Country");
		return new ResponseEntity<>(addServ.getAddressStatusBySpecificCountry(country), HttpStatus.OK);
	}
}
