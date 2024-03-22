package com.example.java_19_headhunter.service;

import com.example.java_19_headhunter.dto.UserDto;
import com.example.java_19_headhunter.models.User;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Optional;

public interface UserService {


    void updateUser(UserDto user);

    @SneakyThrows
    boolean isEmployer(String email);

    int createUser(UserDto user);

    List<UserDto> getUsers();

    Optional<UserDto> findByEmail(String email);

    Optional<UserDto> findByPhoneNumber(String phoneNumber);

    Optional<UserDto> findByName(String name);

    boolean userExists(String email);
    boolean getUserType(User user);
    boolean getUserType(UserDto userdto);
}