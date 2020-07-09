package com.hcl.ecommerce.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.dto.CategoryDto;
import com.hcl.ecommerce.entity.Category;
import com.hcl.ecommerce.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private static Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public CategoryDto getById(Integer id) {
		logger.info("Getting category by id:::::::::::::::: ");
		Optional<Category> categories = categoryRepository.findById(id);
		if (categories.isPresent()) {
			Category category = categories.get();
			CategoryDto categoryDto = new CategoryDto();
			BeanUtils.copyProperties(category, categoryDto);
			logger.info("category dto:::::::: "+categoryDto);
			return categoryDto;
		} else
			return null;
	}
	
	

}
