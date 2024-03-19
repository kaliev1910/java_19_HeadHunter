package com.example.java_19_headhunter.service;

import com.example.java_19_headhunter.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void updateUser(UserDto user);
    void createUser(UserDto user);
    List<UserDto> getUsers();
    Optional<UserDto> findByEmail(String email);
    Optional<UserDto> findByPhoneNumber(String phoneNumber);
    Optional<UserDto> findByName(String name);
    boolean userExists(String email);
}