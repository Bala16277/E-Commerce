package com.hcl.ecommerce.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.dto.OrderDto;
import com.hcl.ecommerce.dto.OrderRequestDto;
import com.hcl.ecommerce.dto.OrderResponseDto;
import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.OrderRepository;
import com.hcl.ecommerce.repository.ProductRepository;
import com.hcl.ecommerce.repository.UserRepository;


@Service
public class OrderServiceImpl implements OrderService {
	
	private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;

	public OrderResponseDto buyProduct(OrderRequestDto orderRequestDto) throws IllegalAccessException, InvocationTargetException {
		logger.info("Inside buy product method:::::: ");
		OrderResponseDto orderResponseDto = new OrderResponseDto();
		Order order = new Order();
		User user;
		Product product;
		user = userRepository.findByUserId(orderRequestDto.getUserId());
		product = productRepository.findByProductId(orderRequestDto.getProductId());
		BeanUtils.copyProperties(order, orderRequestDto);
		order.setUser(user);
		order.setProduct(product);
		order.setTotalPrice(orderRequestDto.getProductQuantity()*product.getProductPrice());
		orderRepository.save(order);
		BeanUtils.copyProperties(orderResponseDto, order);
		BeanUtils.copyProperty(orderResponseDto, "productName", order.getProduct().getProductName());
		BeanUtils.copyProperty(orderResponseDto, "userName", order.getUser().getUserName());
		orderResponseDto.setMessage("Order has been placed successfully");
		orderResponseDto.setStatusCode(HttpStatus.CREATED.value());
		return orderResponseDto;
	}
	
	public List<OrderDto> getOrdersByUserId(Integer userId) throws IllegalAccessException, InvocationTargetException {
		
		User user = userRepository.findByUserId(userId);
		List<Order> orders = orderRepository.findByUser(user);
		List<OrderDto> orderDtos = new ArrayList<>();
		for (Order order : orders) {
			OrderDto orderDto = new OrderDto();
			BeanUtils.copyProperties(orderDto, order);
			BeanUtils.copyProperty(orderDto, "userId", order.getUser().getUserId());
			BeanUtils.copyProperty(orderDto, "productId", order.getProduct().getProductId());
			orderDtos.add(orderDto);
		}
		
		return orderDtos;
		
	}
	
	
}
