package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao{
	
	@Autowired
    private EntityManager entityManager;

    private Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> loadCategories(String mainCategoryName) {
		return getCurrentSession().createQuery("SELECT c FROM Category c, MainCategory m where c.mainCategoryId = m.id and m.name = \'"+mainCategoryName+"\'").list();
	}
	
}
