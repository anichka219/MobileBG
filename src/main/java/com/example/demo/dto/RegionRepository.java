package com.example.demo.dto;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region,Long>{
	
}
