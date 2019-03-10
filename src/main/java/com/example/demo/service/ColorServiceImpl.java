package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ColorRepository;
import com.example.demo.models.Color;

@Service
public class ColorServiceImpl {
	
	@Autowired
	private ColorRepository repo;
	
	 public List<Color> getColors(){
		 return repo.findAll();
	 }
}
