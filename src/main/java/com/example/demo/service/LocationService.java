package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Location;

public interface LocationService {
	
	List<Location> loadLocations(String regionName);
}
