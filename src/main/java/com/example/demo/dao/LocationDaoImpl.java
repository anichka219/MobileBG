package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Location;

@Repository
public class LocationDaoImpl implements LocationDao{

	@Autowired
    private EntityManager entityManager;

    private Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Location> loadLocations(String regionName) {
		
		return getCurrentSession().createQuery("SELECT l FROM Location l, Region r where l.regionId = r.id and r.name = \'"+regionName+"\'").list();
	}

}
