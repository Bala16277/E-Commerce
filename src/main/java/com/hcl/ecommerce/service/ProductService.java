package com.hcl.ecommerce.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.hcl.ecommerce.dto.ProductDto;
import com.hcl.ecommerce.dto.ProductRequestDto;
import com.hcl.ecommerce.dto.ProductResponseDto;

public interface ProductService {
	
	public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws IllegalAccessException, InvocationTargetException;

	public List<ProductDto> searchProduct(String productName) throws IllegalAccessException, InvocationTargetException;
}
