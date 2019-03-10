package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Brand;
import com.example.demo.models.ModelAdv;

public interface ModelAdvService {
	
	public List<ModelAdv> loadModels(String brandId);
	
	public List<Brand> listBrands();
}
