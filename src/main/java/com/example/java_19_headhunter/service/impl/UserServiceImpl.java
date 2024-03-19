package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.dao.interfaces.UserDao;
import com.example.java_19_headhunter.dto.UserDto;
import com.example.java_19_headhunter.models.User;
import com.example.java_19_headhunter.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public void updateUser(UserDto userDto) {
        User user = fromDto(userDto);
        userDao.updateUser(user);
    }

    @Override
    public void createUser(UserDto userDto) {
        User user = fromDto(userDto);
        userDao.createUser(user);
    }

    @Override
    public List<UserDto> getUsers() {
        return userDao.getUsers().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        return userDao.findByEmail(email).map(this::toDto);
    }

    @Override
    public Optional<UserDto> findByPhoneNumber(String phoneNumber) {
        return userDao.findByPhoneNumber(phoneNumber).map(this::toDto);
    }

    @Override
    public Optional<UserDto> findByName(String name) {
        return userDao.findByName(name).map(this::toDto);
    }

    @Override
    public boolean userExists(String email) {
        return userDao.userExists(email);
    }

    private UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAge(user.getAge());
        userDto.setAvatar(user.getAvatar());
        userDto.setAccountType(user.getAccountType());
        return userDto;
    }

    private User fromDto(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAge(userDto.getAge());
        user.setAvatar(userDto.getAvatar());
        user.setAccountType(userDto.getAccountType());
        return user;
    }
}
