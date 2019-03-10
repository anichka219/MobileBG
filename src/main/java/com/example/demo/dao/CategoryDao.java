package com.example.demo.dao;

import java.util.List;

import com.example.demo.models.Category;

public interface CategoryDao {
	
	List<Category> loadCategories(String mainCategoryName);
}
