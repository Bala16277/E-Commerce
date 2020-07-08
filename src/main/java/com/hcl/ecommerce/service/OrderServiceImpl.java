package com.hcl.ecommerce.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;

	public OrderResponseDto buyOrder(OrderRequestDto orderRequestDto) throws IllegalAccessException, InvocationTargetException {
		OrderResponseDto orderResponseDto = new OrderResponseDto();
		Order order = new Order();
		User user = new User();
		Product product = new Product();
		user = userRepository.findByUserId(orderRequestDto.getUserId());
		product = productRepository.findByProductId(orderRequestDto.getProductId());
		BeanUtils.copyProperties(order, orderRequestDto);
		order.setUser(user);
		order.setProduct(product);
		order.setTotalPrice(orderRequestDto.getProductQuantity()*product.getProductPrice());
		orderRepository.save(order);
		BeanUtils.copyProperties(orderResponseDto, order);
		BeanUtils.copyProperty(orderResponseDto, "productId", order.getProduct().getProductId());
		BeanUtils.copyProperty(orderResponseDto, "userId", order.getUser().getUserId());
		orderResponseDto.setMessage("Order has been placed successfully");
		orderResponseDto.setStatusCode(HttpStatus.CREATED.value());
		return orderResponseDto;
	}
	
}
