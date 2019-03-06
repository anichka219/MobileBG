package com.example.demo.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{
	public User findByEmail(String email);
}
