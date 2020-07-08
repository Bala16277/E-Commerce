package com.hcl.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.dto.OrderRequestDto;
import com.hcl.ecommerce.dto.OrderResponseDto;
import com.hcl.ecommerce.service.OrderService;



@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	OrderService orderService;

	@PostMapping("/getAllOrders")
	public ResponseEntity<OrderResponseDto> buyOrders(@RequestBody OrderRequestDto orderRequestDto) {
		return new ResponseEntity<OrderResponseDto>(orderService.buyOrder(orderRequestDto),HttpStatus.OK);
	}
	
}
