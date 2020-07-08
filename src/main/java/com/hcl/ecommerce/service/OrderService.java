package com.hcl.ecommerce.service;

import com.hcl.ecommerce.dto.OrderRequestDto;
import com.hcl.ecommerce.dto.OrderResponseDto;

public interface OrderService {
	
	public OrderResponseDto buyOrder(OrderRequestDto orderRequestDto);
	
}
