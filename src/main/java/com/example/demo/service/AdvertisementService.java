package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AdvRepository;
import com.example.demo.dto.AdvertisementDto;
import com.example.demo.dto.AdvertisementRepository;
import com.example.demo.models.Advertisement;

@Service
public class AdvertisementService {
	
	@Autowired
	private AdvertisementRepository repo;
	
	public List<Advertisement> getAllAdvertisements() {
		return repo.findAll();
	}

}
