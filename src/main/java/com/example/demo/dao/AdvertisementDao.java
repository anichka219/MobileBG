package com.example.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.models.Advertisement;


public class AdvertisementDao {
//	private JdbcTemplate jdbcTemplate;
//	
//	@GetMapping("/Advertisements")
//	public ResultSet getAllAdvertisements() throws SQLException {
//		Connection con = jdbcTemplate.getDataSource().getConnection();
//		
////		ResultSet rs = con.createStatement().executeQuery("SELECT * FROM advertisements");
////		List<AdvertisementDto> Advertisements = new LinkedList<AdvertisementDto>();
////		
////		while (rs.next()) {
////			Advertisements.add(new AdvertisementDto(rs.getLong(1),rs.getString("modification")));
////		}
//		
//		return con.createStatement().executeQuery("SELECT * FROM models");
//	}
}
