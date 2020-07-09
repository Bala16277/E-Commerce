package com.hcl.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.ecommerce.dto.OrderDto;
import com.hcl.ecommerce.dto.OrderRequestDto;
import com.hcl.ecommerce.dto.OrderResponseDto;
import com.hcl.ecommerce.entity.Category;
import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.OrderRepository;
import com.hcl.ecommerce.repository.ProductRepository;
import com.hcl.ecommerce.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class OrderServiceImplTest {
	
	private static Logger logger = Logger.getLogger(OrderServiceImplTest.class);
	
	@InjectMocks
	OrderServiceImpl orderServiceImpl;
	
	@Mock
	OrderRepository orderRepository;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	ProductRepository productRepository;
	
	@Test
	public void testBuyProduct() {
		try {
			
			OrderResponseDto orderResponseDto = new OrderResponseDto();
			Order order = new Order();
			User user = new User();
			user.setUserId(1);
			user.setUserName("bala");
			user.setAddress("bangalore");
			user.setEmail("bala@gmail.com");
			user.setPassword("bala");
			user.setPhone("989898");
			
			Category category = new Category();
			category.setCategoryId(1);
			category.setCategoryName("electronics");
			
			
			Product product = new Product();
			product.setProductId(20);
			product.setProductDesc("pc");
			product.setProductName("dell");
			product.setProductPrice(1243.00);
			product.setCategory(category);
			
			Mockito.when(userRepository.findByUserId(1)).thenReturn(user);
			Mockito.when(productRepository.findByProductId(20)).thenReturn(product);
			order.setUser(user);
			order.setProduct(product);
			order.setTotalPrice(10*1243.00);
			
			Mockito.when(orderRepository.save(order)).thenReturn(order);
			
			OrderRequestDto orderRequestDto = new OrderRequestDto();
			orderRequestDto.setProductId(20);
			orderRequestDto.setProductQuantity(10);
			orderRequestDto.setUserId(1);
			
			orderResponseDto = orderServiceImpl.buyProduct(orderRequestDto);
			Assert.assertNotNull(orderResponseDto);
			Assert.assertEquals("Order has been placed successfully", orderResponseDto.getMessage());
			
		} catch(Exception e) {
			
			logger.warn(e.getMessage());
		
		}
	}
	
	
	@Test
	public void testGetOrdersByUserId() {
		
		try {
			
			User user = new User();
			user.setUserId(1);
			user.setUserName("bala");
			user.setAddress("bangalore");
			user.setEmail("bala@gmail.com");
			user.setPassword("bala");
			user.setPhone("989898");
			
			Mockito.when(userRepository.findByUserId(1)).thenReturn(user);
			
			Category category = new Category();
			category.setCategoryId(1);
			category.setCategoryName("electronics");
			
			Category category1 = new Category();
			category1.setCategoryId(2);
			category1.setCategoryName("clothes");
			
			
			Product product = new Product();
			product.setProductId(20);
			product.setProductDesc("pc");
			product.setProductName("dell");
			product.setProductPrice(1243.00);
			product.setCategory(category);
			
			Product product1 = new Product();
			product1.setProductId(18);
			product1.setProductDesc("pant");
			product1.setProductName("pant");
			product1.setProductPrice(550.00);
			product1.setCategory(category1);
			
			
			Order order = new Order();
			order.setOrderId(7);
			order.setProduct(product);
			order.setUser(user);
			order.setTotalPrice(12430.00);
			
			Order order1 = new Order();
			order1.setOrderId(1);
			order1.setProduct(product1);
			order1.setUser(user);
			order1.setTotalPrice(750.00);
			
			List<Order> orders = new ArrayList<>();
			orders.add(order);
			orders.add(order1);
			
			List<OrderDto> orderDtos = orderServiceImpl.getOrdersByUserId(1);
			Assert.assertNotNull(orderDtos);
			Assert.assertEquals(orders.get(0).getProduct().getProductId(), orderDtos.get(0).getUserId());
			
			
		}
		catch(Exception e) {
			
		}
		
	}
	

}
