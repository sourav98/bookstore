package com.cg.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.bookstore.entities.Address;
import com.cg.bookstore.repository.IAddressRepository;



@ExtendWith(SpringExtension.class)
public class AddressServiceMockitoTest {

	@InjectMocks
	AddressServiceImpl addService;

	@MockBean
	IAddressRepository addRepository;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testMockGetAllAddresses() {
		Address add1 = new Address();
		add1.setAddressId(1);
		add1.setAddress("Police Bazar");
		add1.setCity("Shillong");
		add1.setCountry("India");
		add1.setPincode("600001");
		List<Address> addressList = new ArrayList<>();
		addressList.add(add1);
		Mockito.when(addRepository.findAll()).thenReturn(addressList);
		assertEquals(1, addressList.size());
		assertEquals(1, addressList.get(0).getAddressId());
		assertEquals("Police Bazar", addressList.get(0).getAddress());
		assertEquals("Shillong", addressList.get(0).getCity());
		assertEquals("India", addressList.get(0).getCountry());
		assertEquals("600001", addressList.get(0).getPincode());

	}

	@Test
	void testMockitoGetAddressById() {
		Address add2 = new Address();
		add2.setAddressId(1);
		add2.setAddress("Garali");
		add2.setCity("Jorhat");
		add2.setCountry("India");
		add2.setPincode("736001");
		Address response = new Address();
		Mockito.when(addRepository.findById(1)).thenReturn(Optional.of(add2));
		response = addService.getById(1);
		assertEquals(1, response.getAddressId());
		assertEquals("Garali", response.getAddress());
		assertEquals("Jorhat", response.getCity());
		assertEquals("India", response.getCountry());
		assertEquals("736001", response.getPincode());

	}

	@Test
	void testMockitoAddAddress() {
		Address add3 = new Address();
		add3.setAddressId(1);
		add3.setAddress("Napukhuri");
		add3.setCity("Tinsukia");
		add3.setCountry("India");
		add3.setPincode("716001");
		Address response = new Address();
		Mockito.when(addRepository.save(add3)).thenReturn(add3);
		response = addService.addAddress(add3);
		assertEquals(1, response.getAddressId());
		assertEquals("Napukhuri", response.getAddress());
		assertEquals("Tinsukia", response.getCity());
		assertEquals("India", response.getCountry());
		assertEquals("716001", response.getPincode());

	}

	@Test
	void testMockitoUpdateCityById() {
		Address add4 = new Address();
		add4.setAddressId(1);
		add4.setAddress("Napukhuri");
		add4.setCity("Tinsukia");
		add4.setCountry("India");
		add4.setPincode("716001");
		Address response = new Address();
		Mockito.when(addRepository.findById(1)).thenReturn(Optional.of(add4));
		Mockito.when(addRepository.save(add4)).thenReturn(add4);
		response = addService.updateCityById(1, "Duliajan");
		assertEquals(1, response.getAddressId());
		assertEquals("Napukhuri", response.getAddress());
		assertEquals("Duliajan", response.getCity());
		assertEquals("India", response.getCountry());
		assertEquals("716001", response.getPincode());

	}

	@Test
	void testMockitoUpdateCountryById() {
		Address add5 = new Address();
		add5.setAddressId(1);
		add5.setAddress("Napukhuri");
		add5.setCity("Tinsukia");
		add5.setCountry("India");
		add5.setPincode("716001");
		Address response = new Address();
		Mockito.when(addRepository.findById(1)).thenReturn(Optional.of(add5));
		Mockito.when(addRepository.save(add5)).thenReturn(add5);
		response = addService.updateCountryById(1, "NA");
		assertEquals(1, response.getAddressId());
		assertEquals("Napukhuri", response.getAddress());
		assertEquals("Tinsukia", response.getCity());
		assertEquals("NA", response.getCountry());
		assertEquals("716001", response.getPincode());

	}

	@Test
	void testMockitoUpdatePincodeById() {
		Address add6 = new Address();
		add6.setAddressId(1);
		add6.setAddress("Napukhuri");
		add6.setCity("Tinsukia");
		add6.setCountry("India");
		add6.setPincode("716001");
		Address response = new Address();
		Mockito.when(addRepository.findById(1)).thenReturn(Optional.of(add6));
		Mockito.when(addRepository.save(add6)).thenReturn(add6);
		response = addService.updatePincodeById(1, "123456");
		assertEquals(1, response.getAddressId());
		assertEquals("Napukhuri", response.getAddress());
		assertEquals("Tinsukia", response.getCity());
		assertEquals("India", response.getCountry());
		assertEquals("123456", response.getPincode());

	}

	@Test
	void testMockitoUpdateAddress() {
		Address add7 = new Address();
		add7.setAddressId(1);
		add7.setAddress("Napukhuri");
		add7.setCity("Tinsukia");
		add7.setCountry("India");
		add7.setPincode("716001");
		Address response = new Address();
		Mockito.when(addRepository.findById(1)).thenReturn(Optional.of(add7));
		Mockito.when(addRepository.save(add7)).thenReturn(add7);
		response = addService.updateAddress(add7);
		response.setAddress("Digholi Pukhuri");
		response.setCity("Not Tinsukia");
		response.setCountry("Not India");
		response.setPincode("654321");
		assertEquals(1, response.getAddressId());
		assertEquals("Digholi Pukhuri", response.getAddress());
		assertEquals("Not Tinsukia", response.getCity());
		assertEquals("Not India", response.getCountry());
		assertEquals("654321", response.getPincode());

	}

	@Test
	void testMockitoFindByCity() {
		Address add8 = new Address();
		add8.setAddressId(1);
		add8.setAddress("Police Bazar");
		add8.setCity("Shillong");
		add8.setCountry("India");
		add8.setPincode("600001");
		List<Address> addressList = new ArrayList<>();
		addressList.add(add8);
		Mockito.when(addRepository.findByCity("Shillong")).thenReturn(addressList);
		assertEquals(1, addressList.size());
		assertEquals(1, addressList.get(0).getAddressId());
		assertEquals("Police Bazar", addressList.get(0).getAddress());
		assertEquals("Shillong", addressList.get(0).getCity());
		assertEquals("India", addressList.get(0).getCountry());
		assertEquals("600001", addressList.get(0).getPincode());

	}

	@Test
	void testMockitoGetAddressByPincode() {
		Address add9 = new Address();
		add9.setAddressId(1);
		add9.setAddress("Police Bazar");
		add9.setCity("Shillong");
		add9.setCountry("India");
		add9.setPincode("600001");
		ArrayList<Address> addressList = new ArrayList<>();
		addressList.add(add9);
		Mockito.when(addRepository.getAddressByPincode("600001")).thenReturn(addressList);
		assertEquals(1, addressList.size());
		assertEquals(1, addressList.get(0).getAddressId());
		assertEquals("Police Bazar", addressList.get(0).getAddress());
		assertEquals("Shillong", addressList.get(0).getCity());
		assertEquals("India", addressList.get(0).getCountry());
		assertEquals("600001", addressList.get(0).getPincode());
	}

	@Test
	void testMockitoDeleteById() {
		Address add10 = new Address();
		add10.setAddressId(1);
		add10.setAddress("Police Bazar");
		add10.setCity("Shillong");
		add10.setCountry("India");
		add10.setPincode("600001");
		Mockito.when(addRepository.findById(1)).thenReturn(Optional.of(add10));
		Address response = addService.deleteById(1);
		assertEquals(1, response.getAddressId());
		assertEquals("Police Bazar", response.getAddress());
		assertEquals("Shillong", response.getCity());
		assertEquals("India", response.getCountry());
		assertEquals("600001", response.getPincode());

	}
}
