package com.example.java_19_headhunter.dao;

import com.example.java_19_headhunter.models.User;


public interface UserDao {
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);
    User findByFullName(String firstName, String lastName);
    boolean userExists(String email);
}
