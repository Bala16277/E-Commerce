package com.hcl.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.dto.OrderRequestDto;
import com.hcl.ecommerce.dto.OrderResponseDto;
import com.hcl.ecommerce.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;

	public OrderResponseDto buyOrder(OrderRequestDto orderRequestDto) {
		OrderResponseDto orderResponseDto = new OrderResponseDto();
		return orderResponseDto;
	}
	
}
