package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.example.demo.dto"})
@EntityScan(basePackages = {"com.example.demo.models"}) 
@SpringBootApplication
public class MobileBgApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileBgApplication.class, args);
	}

}
