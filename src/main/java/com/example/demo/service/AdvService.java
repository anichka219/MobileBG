package com.example.demo.service;

import com.example.demo.models.Advertisement;

import java.util.List;

public interface AdvService {

    Advertisement save(Advertisement task);

    Boolean delete(long id);

    Advertisement update(Advertisement task);

    Advertisement findById(long id);

    List<Advertisement> findAll();

    List<Advertisement> findByStatus(String status);

    List<Advertisement> findByUserIdStatus(long userId, String status);

//    List<Advertisement> findBetween(int start, int end);

}

