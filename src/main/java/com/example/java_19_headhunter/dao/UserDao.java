package com.example.java_19_headhunter.dao;

import com.example.java_19_headhunter.models.User;

import java.util.List;
import java.util.Optional;


public interface UserDao {
    public List<User> getUsers();
    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByName(String name);
    boolean userExists(String email);
}
