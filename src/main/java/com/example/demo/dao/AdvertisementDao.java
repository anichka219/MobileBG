package com.example.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.models.Advertisement;


public class AdvertisementDao {
	private JdbcTemplate jdbcTemplate;
	private static Connection con; 


	public void addKoza(Advertisement advertisement) throws SQLException{
		con.createStatement().
		executeUpdate("INSERT INTO advertisements values (null)");
		
	}
	
	public Set<Advertisement> getAllAdvertisements() throws SQLException {
		ResultSet r = con.createStatement().executeQuery("SELECT * FROM advertisements");
		Set<Advertisement> result = new HashSet<Advertisement>();
		
		while (r.next()) {
			result.add(new Advertisement());
		}
		
		return result;
	}
}
