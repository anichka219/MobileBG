package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.RegionRepository;
import com.example.demo.models.Region;

@Service
public class RegionServiceImpl {
	
	 @Autowired
	 private RegionRepository regionRepository;
	 
	 public List<Region> getAllRegions(){
		 return regionRepository.findAll();
	 }
}
