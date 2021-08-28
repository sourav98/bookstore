package com.cg.bookstore.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.dto.AddressStatusDto;
import com.cg.bookstore.entities.Address;
import com.cg.bookstore.entities.BookOrder;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Integer>
{
	List<Address> findByCity(String city); // CustomMethod

	@Query("select a from Address a where a.pincode=:pincode")
	ArrayList<Address> getAddressByPincode(@Param("pincode") String pincode); // JPQL Query

	@Query(value = "select new com.cg.bookstore.dto.AddressStatusDto(b.orderId, b.status, a.city) from Address a inner join BookOrder b on b.shippingAddress.addressId=a.addressId")
	List<AddressStatusDto> getAddressStatus();

	@Query(value = "select new com.cg.bookstore.dto.AddressStatusDto(b.orderId, b.status, a.country) from Address a inner join BookOrder b on b.shippingAddress.addressId=a.addressId")
	List<AddressStatusDto> getAddressStatusByCountry();

	@Query(value = "select new com.cg.bookstore.dto.AddressStatusDto(b.orderId, b.status, a.city) from Address a inner join BookOrder b on b.shippingAddress.addressId=a.addressId where a.city=:city")
	List<AddressStatusDto> getAddressStatusBySpecificCity(@Param("city") String city);

	@Query(value = "select new com.cg.bookstore.dto.AddressStatusDto(b.orderId, b.status, a.city) from Address a inner join BookOrder b on b.shippingAddress.addressId=a.addressId where a.country=:country")
	List<AddressStatusDto> getAddressStatusBySpecificCountry(@Param("country") String country);

}