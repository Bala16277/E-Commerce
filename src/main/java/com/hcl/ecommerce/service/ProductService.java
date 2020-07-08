package com.hcl.ecommerce.service;

import java.lang.reflect.InvocationTargetException;

import com.hcl.ecommerce.dto.ProductRequestDto;
import com.hcl.ecommerce.dto.ProductResponseDto;

public interface ProductService {
	
	public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws IllegalAccessException, InvocationTargetException;

}
