package com.example.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controllers.AdvController;
import com.example.demo.dao.ModelDao;
import com.example.demo.models.Brand;
import com.example.demo.models.ModelAdv;

@Service
public class ModelAdvServiceImpl implements ModelAdvService{

    private static final Logger logger = LoggerFactory.getLogger(ModelAdvServiceImpl.class);
	@Autowired
	private ModelDao modelDao;
	
	@Override
	@Transactional
	public List<ModelAdv> loadModels(String brandId) {
		logger.info("loadModels");
		return modelDao.loadModels(brandId);
	}

	@Override
	@Transactional
	public List<Brand> listBrands() {
		return modelDao.listBrands();
	}

}
