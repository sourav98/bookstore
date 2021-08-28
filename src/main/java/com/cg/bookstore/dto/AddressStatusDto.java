package com.cg.bookstore.dto;

public class AddressStatusDto {
	private int orderId;
	private String status;
	private String city;
	
	public AddressStatusDto() {}

	public AddressStatusDto(int orderId, String status, String city) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.city = city;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "AddressStatusDto [orderId=" + orderId + ", status=" + status + ", city=" + city + "]";
	}
	
	
	
}
