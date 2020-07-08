package com.hcl.ecommerce.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.dto.ProductRequestDto;
import com.hcl.ecommerce.dto.ProductResponseDto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductRepository productRepository;
	
	public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws IllegalAccessException, InvocationTargetException {
		logger.info("Inside addproduct method of product service");
		ProductResponseDto productResponseDto = new ProductResponseDto();
		Product product = new Product();
		BeanUtils.copyProperty(product, "productName", productRequestDto.getProductName());
		BeanUtils.copyProperty(product, "productPrice", productRequestDto.getProductPrice());
		BeanUtils.copyProperty(product, "productDesc", productRequestDto.getProductDesc());
		BeanUtils.copyProperty(product, "categoryId", productRequestDto.getCategoryId());
		productRepository.save(product);
		productResponseDto.setMessage("Product has been added successfully");
		productResponseDto.setStatusCode(HttpStatus.CREATED.value());
		
		
		return productResponseDto;
	}

}
