package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.LocationDao;
import com.example.demo.models.Location;

@Service
public class LocationServiceImpl implements LocationService{

	@Autowired
	private LocationDao locationDao;
	@Override
	public List<Location> loadLocations(String regionName) {
		return locationDao.loadLocations(regionName);
	}

}
