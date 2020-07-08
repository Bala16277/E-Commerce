package com.hcl.ecommerce.controller;

import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.dto.ProductRequestDto;
import com.hcl.ecommerce.dto.ProductResponseDto;
import com.hcl.ecommerce.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private static Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/")
	public ResponseEntity<ProductResponseDto> addProduct(ProductRequestDto productRequestDto) throws IllegalAccessException, InvocationTargetException {
		logger.info("Inside add product method of controller ");
		ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
		return new ResponseEntity<ProductResponseDto>(productResponseDto,HttpStatus.CREATED);
	}

}
