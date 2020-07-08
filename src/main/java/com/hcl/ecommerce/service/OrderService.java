package com.hcl.ecommerce.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.hcl.ecommerce.dto.OrderDto;
import com.hcl.ecommerce.dto.OrderRequestDto;
import com.hcl.ecommerce.dto.OrderResponseDto;

public interface OrderService {
	
	public OrderResponseDto buyProduct(OrderRequestDto orderRequestDto) throws IllegalAccessException, InvocationTargetException;
	
	public List<OrderDto> getOrdersByUserId(Integer userId) throws IllegalAccessException, InvocationTargetException;
	
}
