package com.hcl.ecommerce.service;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.ecommerce.dto.CategoryDto;
import com.hcl.ecommerce.entity.Category;
import com.hcl.ecommerce.repository.CategoryRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CategoryServiceImplTest {
	
	@InjectMocks
	CategoryServiceImpl categoryServiceImpl;
	
	@Mock
	CategoryRepository categoryRepository;
	
	@Test
	public void testGetById() {
		
		CategoryDto categoryDto = new CategoryDto();
		
		Category category = new Category();
		category.setCategoryId(1);
		category.setCategoryName("electronics");
//		Mockito.when(categoryRepository.findById(1).thenReturn());
		
		
	}

}
