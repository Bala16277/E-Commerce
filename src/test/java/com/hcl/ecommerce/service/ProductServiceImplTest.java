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

import com.hcl.ecommerce.dto.ProductDto;
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
			logger.info("after the test method::::::::::: ");
			logger.info(productResponseDto.getMessage());
			Assert.assertNotNull(productResponseDto);
			Assert.assertEquals("Product has been added successfully", productResponseDto.getMessage());
			
		} catch (Exception e) {

			logger.warn(e.getMessage());
		}
	}

	@Test
	public void testSearchProduct() {
		try {
		Category category = new Category();
		category.setCategoryId(1);
		category.setCategoryName("laptop");
		Product product = new Product();
		product.setProductId(18);
		product.setProductName("dell");
		product.setProductDesc("laptop");
		product.setProductPrice(1234.00);
		product.setCategory(category);

		Product product1 = new Product();
		product1.setProductId(19);
		product1.setProductName("dell");
		product1.setProductDesc("pc");
		product1.setProductPrice(1243.00);
		product1.setCategory(category);

		List<Product> products = new ArrayList<>();
		products.add(product);
		products.add(product1);
		logger.info("products list::::::::::: "+products.get(0).getProductId());
		Mockito.when(productRepository.findByProductNameContains("dell")).thenReturn(products);
		List<ProductDto> productDtos = productServiceImpl.searchProduct("dell"); 
		Assert.assertNotNull(productDtos);
		Assert.assertEquals(products.get(0).getProductId(), productDtos.get(0).getProductId());
		}
		catch (Exception e) {
			logger.warn(e.getMessage());
		}

	}

}
