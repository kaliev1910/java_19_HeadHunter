package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.dao.interfaces.UserDao;
import com.example.java_19_headhunter.dto.UserDto;
import com.example.java_19_headhunter.models.User;
import com.example.java_19_headhunter.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        User user;
        try {
            user = fromDto(userDto);
            userDao.updateUser(user);
            log.info("User with id {} has been updated", userDto.getId());
        } catch (Exception e) {
            log.error("Error while trying to update user with id {}", userDto.getId(), e);
            throw e;

        }
    }

    @Override
    public void createUser(UserDto userDto) {
        User user;
        try {
            user = fromDto(userDto);
            userDao.createUser(user);
            log.info("User with email {} has been created", userDto.getEmail());
        } catch (Exception e) {
            log.error("Error while trying to create user", e);
            throw e;
        }
    }

    @Override
    public List<UserDto> getUsers() {
        try {
            var users = userDao.getUsers();
            log.info("Retrieved {} users", users.size());
            return users.stream().map(this::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error while trying to get users", e);
            throw e;
        }
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        try {
            var user = userDao.findByEmail(email);
            log.info("Retrieved user by email: {}", email);
            return user.map(this::toDto);
        } catch (Exception e) {
            log.error("Error while trying to find user by email: {}", email, e);
            throw e;
        }
    }

    @Override
    public Optional<UserDto> findByPhoneNumber(String phoneNumber) {
        try {
            var user = userDao.findByPhoneNumber(phoneNumber);
            log.info("Retrieved user by phone number: {}", phoneNumber);
            return user.map(this::toDto);
        } catch (Exception e) {
            log.error("Error while trying to find user by phone number: {}", phoneNumber, e);
            throw e;
        }
    }

    @Override
    public Optional<UserDto> findByName(String name) {
        try {
            var user = userDao.findByName(name);
            log.info("Retrieved user by name: {}", name);
            return user.map(this::toDto);
        } catch (Exception e) {
            log.error("Error while trying to find user by name: {}", name, e);
            throw e;
        }
    }

    @Override
    public boolean userExists(String email) {
        try {
            var exists = userDao.userExists(email);
            log.info("Checked if user exists by email: {}", email);
            return exists;
        } catch (Exception e) {
            log.error("Error while trying to check if user exists by email: {}", email, e);
            throw e;
        }
    }

    private UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(user.getPassword())
                .age(user.getAge())
                .avatar(user.getAvatar())
                .accountType(user.getAccountType())
                .build();
    }

    private User fromDto(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .age(userDto.getAge())
                .avatar(userDto.getAvatar())
                .accountType(userDto.getAccountType())
                .build();
    }
    }
