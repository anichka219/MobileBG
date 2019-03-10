package com.example.demo.dao;

import java.util.List;

import com.example.demo.models.Brand;
import com.example.demo.models.ModelAdv;

public interface ModelDao {
	
	public List<ModelAdv> loadModels(String brandId);
	public List<Brand> listBrands();
}
