package com.hcl.ecommerce.service;

import java.lang.reflect.InvocationTargetException;

import com.hcl.ecommerce.dto.OrderRequestDto;
import com.hcl.ecommerce.dto.OrderResponseDto;

public interface OrderService {
	
	public OrderResponseDto buyOrder(OrderRequestDto orderRequestDto) throws IllegalAccessException, InvocationTargetException;
	
}
