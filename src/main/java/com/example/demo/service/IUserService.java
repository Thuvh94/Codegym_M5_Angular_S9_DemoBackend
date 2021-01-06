package com.example.demo.service;

import com.example.demo.model.User;

import java.util.Optional;

public interface IUserService {
    Iterable<User> findAll();
    Optional<User> findById(Long id);
    User save (User user);
    void remove (Long id);
}
