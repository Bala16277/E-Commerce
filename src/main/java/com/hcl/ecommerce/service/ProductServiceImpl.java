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

import com.hcl.ecommerce.dto.ProductDto;
import com.hcl.ecommerce.dto.ProductRequestDto;
import com.hcl.ecommerce.dto.ProductResponseDto;
import com.hcl.ecommerce.entity.Category;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.repository.CategoryRepository;
import com.hcl.ecommerce.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {
	
	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws IllegalAccessException, InvocationTargetException {
		logger.info("Inside addproduct method of product service");
		ProductResponseDto productResponseDto = new ProductResponseDto();
		logger.info("ProductRequestDto=========: "+productRequestDto.getProductName());
		Product product = new Product();
		Category category;
		category = categoryRepository.findByCategoryId(productRequestDto.getCategoryId());
		BeanUtils.copyProperties(product,productRequestDto);
		BeanUtils.copyProperty(product,"category", category);
		productRepository.save(product);
		BeanUtils.copyProperties(productResponseDto,productRequestDto);
		productResponseDto.setMessage("Product has been added successfully");
		productResponseDto.setStatusCode(HttpStatus.CREATED.value());
		return productResponseDto;
	}
	
	public List<ProductDto> searchProduct(String productName) throws IllegalAccessException, InvocationTargetException {
		logger.info("Inside search product method of service implemention:::::::: ");
		List<Product> products = productRepository.findByProductNameContains(productName);
		List<ProductDto> productDtos = new ArrayList<>();
		for (Product product : products) {
			ProductDto productDto = new ProductDto();
			BeanUtils.copyProperties(productDto, product);
			logger.info("Category Id:::::: "+product.getCategory().getCategoryId());
			BeanUtils.copyProperty(productDto, "categoryId", product.getCategory().getCategoryId());
			logger.info("Category Id:::::: "+product.getCategory().getCategoryId());
			productDtos.add(productDto);
		}
		return productDtos;
	}

}
