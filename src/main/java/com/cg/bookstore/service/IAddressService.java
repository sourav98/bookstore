package com.cg.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.bookstore.dto.AddressCustomerDto;
import com.cg.bookstore.dto.AddressDto;
import com.cg.bookstore.dto.AddressStatusDto;
import com.cg.bookstore.entities.Address;



public interface IAddressService {
	Address addAddress(AddressDto addressDto); //implementation done, exception handling done, test 1 done, Mockito test done, manual test case done
	Address getById(int addressId);  //implementation done, exception handling done, test 1 done, Mockito test done, manual test done
	Address deleteById(int addressId);	//implementation done, exception handling done, test 1 done, manual test case done
	List<Address> getAllAddresses();  //implementation done, exception handling done, test 1 done, Mockito test done, manual test case done
	Address updateCityById(int addressId, String city); //implementation done, exception handling done, test 1 done, Mockito test done,
	Address updateCountryById(int addressId, String country); //implementation done, exception handling done, test 1 done, Mockito test done
	Address updatePincodeById(int addressId, String pincode); //implementation done, exception handling done, test 1 done, Mockito test done
	Address updateAddress(Address address); //implementation done, exception handling done, test 1 done, Mockito test done
	List<Address> findByCity(String city); //implementation done, exception handling done, test 1 done, Mockito test done
	ArrayList<Address> getAddressByPincode(String pincode); //implementation done, exception handling done, test 1 done, Mockito test done
	//DTO
	List<AddressStatusDto> getAddressStatus(); //implementation done, exception handling done, Mockito test done
	List<AddressStatusDto> getAddressStatusByCountry(); //implementation done, exception handling done, Mockito test done
	List<AddressStatusDto> getAddressStatusBySpecificCity(String city); //implementation done, exception handling done, Mockito test done
	List<AddressStatusDto> getAddressStatusBySpecificCountry(String country); //implementation done, exception handling done, Mockito test done
//	List<AddressCustomerDto> getAddressCustomer();
	List<AddressDto> getAddressByCustomerId(int customerId);
}



