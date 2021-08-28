package com.cg.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.bookstore.entities.Address;



@SpringBootTest
public class AddressServiceTest {
	
	@Autowired
	IAddressService addServ;

	
	@Test
	void testAddAddress() {
		Address address = new Address(10,"Link Road", "Guwahati", "India", "750000");
		Address persistedAdd = addServ.addAddress(address);
		assertEquals(35, persistedAdd.getAddressId());
		assertEquals("Link Road", persistedAdd.getAddress());
		assertEquals("Guwahati", persistedAdd.getCity());
		assertEquals("India", persistedAdd.getCountry());
		assertEquals("750000", persistedAdd.getPincode());
	}
	
	
	@Test
	void testDeleteByID() {
		Address address = new Address(10,"Link Road", "Guwahati", "Nepal", "750000");
		Address persistedAdd = addServ.addAddress(address);
		Address response = addServ.deleteById(persistedAdd.getAddressId());
		assertEquals("Link Road", response.getAddress());
	}
	
	@Test
	void testGetAllAddresses() {
		List<Address> response = addServ.getAllAddresses();
		assertEquals(10,response.size());
	}
	
	
	@Test
	void testGetById() {
		Address response = addServ.getById(8);
		assertEquals("Boiragimoth", response.getAddress());
		assertEquals("Itanagar", response.getCity());
		assertEquals("India", response.getCountry());
		assertEquals("786005", response.getPincode());
	}
	
	@Test
	void testUpdateCityById() {
		Address response = addServ.updateCityById(22, "Kamakhya");
		Address updatedAddress = addServ.getById(22);
		assertEquals("Link Road", updatedAddress.getAddress());
		assertEquals("Kamakhya", updatedAddress.getCity());
		assertEquals("Bharat", updatedAddress.getCountry());
		assertEquals("666666", updatedAddress.getPincode());
		
	}
	
	@Test
	void testUpdateCountryById() {
		Address response = addServ.updateCountryById(22, "Bharat");
		Address updatedAddress = addServ.getById(22);
		assertEquals("Link Road", updatedAddress.getAddress());
		assertEquals("Kamakhya", updatedAddress.getCity());
		assertEquals("Bharat", updatedAddress.getCountry());
		assertEquals("666666", updatedAddress.getPincode());
		
	}
	
	
	@Test
	void testUpdatePincodeById() {
		Address response = addServ.updatePincodeById(22, "666666");
		Address updatedAddress = addServ.getById(22);
		assertEquals("Link Road", updatedAddress.getAddress());
		assertEquals("Kamakhya", updatedAddress.getCity());
		assertEquals("Bharat", updatedAddress.getCountry());
		assertEquals("666666", updatedAddress.getPincode());
		
	}
	
	
	@Test
	void testUpdateAddress() {
		Address address = new Address();
		address.setAddressId(6);
		address.setAddress("Mancotta");
		address.setCity("Jorhat");
		address.setCountry("Bharat");
		address.setPincode("768001");
		addServ.updateAddress(address);
		Address updatedAddress = addServ.getById(6);
		assertEquals("Mancotta", updatedAddress.getAddress());
		assertEquals("Jorhat", updatedAddress.getCity());
		assertEquals("Bharat", updatedAddress.getCountry());
		assertEquals("768001", updatedAddress.getPincode());
		
	}
	
	@Test
	void testfindByCity() {
		List<Address> response = addServ.findByCity("Dibrugarh");
		assertEquals(2,response.size());
	}
	
	
	@Test
	void testGetAddressByPincode() {
		ArrayList<Address> response = addServ.getAddressByPincode("786001");
		assertEquals(1, response.size());
	}
	
	

}
