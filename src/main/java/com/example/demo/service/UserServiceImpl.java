package com.example.demo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import com.example.demo.dto.UserRepository;
import com.example.demo.models.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	 @Autowired
	 private UserRepository userRepository;

	 @Override
	 public User save(User user) {
	      return userRepository.save(user);
	  }

	    @Override
	    public Boolean delete(long id) {
	      if (userRepository.existsById(id)) {
	           userRepository.deleteById(id);
	           return true;
	      }
	        return false;
	    }

	    @Override
	    public User update(User user) {
	        return userRepository.save(user);
	    }

	    @Override
	    public User findById(long id) {
	        return userRepository.findById(id).get();
	    }

	    @Override
	    public User findByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }

	    @Override
	    public Collection<User> findAll() {
	        Iterable<User> itr = userRepository.findAll();
	        return (Collection<User>) itr;
	    }


}
