package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controllers.AdvController;
import com.example.demo.dto.AdvRepository;
import com.example.demo.models.Advertisement;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AdvServiceImpl implements AdvService {

    private static final Logger logger = LoggerFactory.getLogger(AdvServiceImpl.class);
    @Autowired
    private AdvRepository advRepository;

    @Override
    public Advertisement save(Advertisement task) {
        return advRepository.save(task);
    }

    @Override
    public Boolean delete(long id) {

        if (advRepository.existsById(id)) {
        	advRepository.deleteById(id);
        	
            return true;
        }
        return false;
    }

    @Override
    public Advertisement update(Advertisement task) {
        return advRepository.save(task);
    }

    @Override
    public Advertisement findById(long id) {
        return advRepository.findById(id).get();
    }

    @Override
    public List<Advertisement> findAll() {
        return (List<Advertisement>) advRepository.findAll();
    }

    @Override
    public List<Advertisement> findByStatus(String status) {
        return advRepository.findByStatus(status);
    }

    @Override
    public List<Advertisement> findByUserIdStatus(long userId, String status) {
        return  advRepository.findByUserIdAndStatus(userId, status);
    }
    
    @Transactional
	@Override
	public List<Advertisement> findBetween(int start, int end) {
		 return advRepository.findBetween(start, end);
	}
	
}
