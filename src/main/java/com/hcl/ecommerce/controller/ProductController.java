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

import com.hcl.ecommerce.dto.ProductDto;
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
	public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDto) throws IllegalAccessException, InvocationTargetException {
		logger.info("Inside add product method of product controller::::::::::::::::::: ");
		ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
		return new ResponseEntity<>(productResponseDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<ProductDto>> searchProducts(@RequestParam String productName) throws IllegalAccessException, InvocationTargetException {
		logger.info("Inside add product method of controller ");
		List<ProductDto> productDtos = productService.searchProduct(productName);
		return new ResponseEntity<>(productDtos, HttpStatus.OK);
	}

}
