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
import org.springframework.beans.factory.annotation.Autowired;

import com.hcl.ecommerce.dto.ProductRequestDto;
import com.hcl.ecommerce.dto.ProductResponseDto;
import com.hcl.ecommerce.entity.Category;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.repository.CategoryRepository;
import com.hcl.ecommerce.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductServiceImplTest {

	private static Logger logger = Logger.getLogger(ProductServiceImplTest.class);

	@Autowired
	@InjectMocks
	ProductServiceImpl productServiceImpl;

	@Mock
	ProductRepository productRepository;

	@Mock
	CategoryRepository categoryRepository;

	@Test
	public void testAddProductNew() {
		try {
			ProductResponseDto productResponseDto = new ProductResponseDto();
			ProductRequestDto productRequestDto = new ProductRequestDto();
			Product product = new Product();
			Category category = new Category();
			category.setCategoryId(1);
			category.setCategoryName("laptop");
			Mockito.when(categoryRepository.findByCategoryId(1)).thenReturn(category);
			product.setProductId(15);
			product.setProductName("pc");
			product.setProductDesc("pc");
			product.setProductPrice(222.00);
			product.setCategory(category);
			Mockito.when(productRepository.save(product)).thenReturn(product);
			productRequestDto.setCategoryId(category.getCategoryId());
			productRequestDto.setProductDesc(product.getProductDesc());
			productRequestDto.setProductPrice(product.getProductPrice());
			productRequestDto.setProductName(product.getProductName());
			productResponseDto = productServiceImpl.addProduct(productRequestDto);
			Assert.assertNotNull(productResponseDto);
			Assert.assertEquals("Product has been added successfully", productResponseDto.getMessage());
			
		} catch (Exception e) {

			logger.warn(e.getMessage());
		}
	}

	@Test
	public void testSearchProduct() {
		Category category = new Category();
		category.setCategoryId(1);
		category.setCategoryName("laptop");
		Product product = new Product();
		product.setProductId(1);
		product.setProductName("hp laptop");
		product.setProductPrice(123.00);
		product.setCategory(category);

		Product product1 = new Product();
		product.setProductId(2);
		product.setProductName("acer laptop");
		product.setProductPrice(232.00);
		product.setCategory(category);

		List<Product> products = new ArrayList();
		products.add(product);
		products.add(product1);

		Mockito.when(productRepository.findByProductNameContains("laptop")).thenReturn(products);

	}

}
