package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Category;

public interface CategoryService {
	
	public List<Category> loadCategories(String mainCategoryName);
}
