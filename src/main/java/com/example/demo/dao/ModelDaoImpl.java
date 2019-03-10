package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.controllers.AdvController;
import com.example.demo.models.Brand;
import com.example.demo.models.ModelAdv;

@Repository
public class ModelDaoImpl implements ModelDao{

    private static final Logger logger = LoggerFactory.getLogger(AdvController.class);
	@Autowired
    private EntityManager entityManager;

    private Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<ModelAdv> loadModels(String brandId) {
		logger.info("bfore");
		return getCurrentSession().createQuery("SELECT m FROM ModelAdv m, Brand b where m.brandId = b.id and b.name = \'" + brandId + "\'").list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Brand> listBrands() {
		return getCurrentSession().createQuery("from Brand").list();
	}

}
