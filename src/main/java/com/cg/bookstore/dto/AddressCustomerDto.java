package com.cg.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressCustomerDto {
private int customerId;
private String fullName;
private String address;
private String city;
private String pincode;
}
