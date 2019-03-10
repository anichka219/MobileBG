package com.example.demo.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color,Long>{

}
