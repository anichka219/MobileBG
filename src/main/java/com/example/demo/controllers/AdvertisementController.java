package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.service.AdvertisementService;

@RestController
public class AdvertisementController {
	
	@Autowired
	private AdvertisementService advertisementService;

	@GetMapping("/advertisements")
	public List<AdvertisementDto> getAllCourses() {
	   	System.out.println("here222!!!!!!!");
		return advertisementService.getAllAdvertisements();
	}

}
