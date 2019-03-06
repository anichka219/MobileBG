package com.example.demo.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.MainCategory;

@Repository
public interface MainCategoryRepository extends JpaRepository<MainCategory, Long> {

}
