package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MainCategoryRepository;
import com.example.demo.models.MainCategory;

@Service
public class MainCategoryServiceImpl {
	@Autowired
	 private MainCategoryRepository repo;
	 
	 public List<MainCategory> getAllMainCategories(){
		 return repo.findAll();
	 }
}
