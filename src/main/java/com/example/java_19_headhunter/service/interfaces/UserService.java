package com.example.java_19_headhunter.service.interfaces;

import com.example.java_19_headhunter.dto.basicDtos.UserDto;
import com.example.java_19_headhunter.models.User;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Optional;

public interface UserService {


    void updateUser(UserDto user);

    @SneakyThrows

    int createUser(UserDto user);

    List<UserDto> getUsers();

    Optional<UserDto> findByEmail(String email);


    Optional<UserDto> findByName(String name);

    boolean userExists(String email);


    boolean getUserType(UserDto userdto);
}