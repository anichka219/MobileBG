package com.example.demo.dao;

import java.util.List;

import com.example.demo.models.Location;

public interface LocationDao {
	List<Location> loadLocations(String regionName);
}
