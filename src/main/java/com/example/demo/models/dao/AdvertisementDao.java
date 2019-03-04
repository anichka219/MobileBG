package com.example.demo.models.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.models.Advertisement;
import com.example.demo.models.dto.AdvertisementDto;

public class AdvertisementDao {
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/Advertisements")
	public List<AdvertisementDto> getAllAdvertisements() throws SQLException {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		
		ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Advertisements");
		List<AdvertisementDto> Advertisements = new LinkedList<AdvertisementDto>();
		
		while (rs.next()) {
			Advertisements.add(new AdvertisementDto(rs.getLong(1), rs.getString("modification")));
		}
		
		return Advertisements;
	}
}
