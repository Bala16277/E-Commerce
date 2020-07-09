package com.hcl.ecommerce.dto;

public class OrderResponseDto {

	private Integer productName;

	private Integer productQuantity;

	private Integer userName;
	
	private Double totalPrice;
	
	private String message;
	
	private int statusCode;


	public Integer getProductName() {
		return productName;
	}

	public void setProductName(Integer productName) {
		this.productName = productName;
	}

	public Integer getUserName() {
		return userName;
	}

	public void setUserName(Integer userName) {
		this.userName = userName;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	
	
}
