package com.hcl.ecommerce.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.dto.OrderDto;
import com.hcl.ecommerce.dto.OrderRequestDto;
import com.hcl.ecommerce.dto.OrderResponseDto;
import com.hcl.ecommerce.service.OrderService;



@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	OrderService orderService;

	@PostMapping("/buyProducts")
	public ResponseEntity<OrderResponseDto> buyProducts(@RequestBody OrderRequestDto orderRequestDto) throws IllegalAccessException, InvocationTargetException {
		logger.info("Inside buy order method:::::::::: ");
		return new ResponseEntity<OrderResponseDto>(orderService.buyProduct(orderRequestDto),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<OrderDto>> getOrdersByUserId(@RequestParam Integer userId) throws IllegalAccessException, InvocationTargetException {
		logger.info("Inside buy order method:::::::::: ");
		List<OrderDto> orderDtos = orderService.getOrdersByUserId(userId);
		return new ResponseEntity<List<OrderDto>>(orderDtos,HttpStatus.OK);
	}
}
