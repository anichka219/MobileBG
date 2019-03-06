package com.example.demo.service;

import java.util.Collection;
import com.example.demo.models.User;

public interface UserService {

    User save(User user);

    Boolean delete(long id);

    User update(User user);

    User findById(long id);

    User findByEmail(String email);

    Collection<User> findAll();
}
