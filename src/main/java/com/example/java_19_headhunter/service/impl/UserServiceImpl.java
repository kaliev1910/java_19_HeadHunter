package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.dto.basicDtos.UserDto;
import com.example.java_19_headhunter.exeptions.UserNotFoundException;
import com.example.java_19_headhunter.models.User;
import com.example.java_19_headhunter.repository.UserRepository;
import com.example.java_19_headhunter.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service

public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public void updateUser(UserDto userDto) {
        User user;
        try {

            user = fromDto(userDto);
            user.setPassword(encoder.encode(user.getPassword()));
            User tempUser = userRepository.findUserByEmail(user.getEmail()).orElseThrow(() -> new UserNotFoundException("User not found"));
            user.setAccountType(tempUser.getAccountType());
            user.setEnabled(tempUser.isEnabled());
            userRepository.save(user);
            log.info("User with email {} has been updated", userDto.getEmail());
        } catch (Exception e) {
            log.error("Error while trying to update user with email {} user {}", userDto.getEmail(), userDto, e);
            throw e;

        }
    }

//    @Override
//    @SneakyThrows
//    public boolean isEmployer(String email) {
//        return userRepository.findUserByEmail(email).map(user -> {
//            Optional<UserDto> userDto = findByEmail(user.getEmail());
//            return userDto.get().getAccountType().equalsIgnoreCase("employer");
//        }).orElseThrow(() -> new UserNotFoundException("Cannot find user"));
//    }

    @Override
    public int createUser(UserDto userDto) {
        User user;
        try {
            user = fromDto(userDto);
            user.setPassword(encoder.encode(userDto.getPassword()));
            userRepository.save(user);
            Optional<User> newUser = userRepository.findUserByEmail(user.getEmail());

            if (newUser.isPresent()) {
                log.info("User with email {} has been created", userDto.getEmail());
                return user.getId();
            } else {
                log.info("User could not be created");
                return 0;
            }
        } catch (Exception e) {
            log.error("Error while trying to create user", e);
            throw e;
        }
    }

    @Override
    public List<UserDto> getUsers() {
        try {
            var users = userRepository.findAll();
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
            var user = userRepository.findUserByEmail(email);
            log.info("Retrieved user by email: {}", email);
            return user.map(this::toDto);
        } catch (Exception e) {
            log.error("Error while trying to find user by email: {}", email, e);
            throw e;
        }
    }

    @Override
    public Optional<UserDto> findByName(String name) {
        try {
            var user = userRepository.findUserByName(name);
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
            var exists = userRepository.existsByEmail(email);
            log.info("Checked if user exists by email: {} result {}", email, exists);
            return exists;
        } catch (Exception e) {
            log.error("Error while trying to check if user exists by email: {}", email, e);
            throw e;
        }
    }
//
//    @Override
//    public boolean getUserType(User user) {
//        try {
//            boolean isApplicant = "applicant".equalsIgnoreCase(user.getAccountType());
//            log.info("Is user an applicant: {}", isApplicant);
//            return isApplicant;
//        } catch (Exception e) {
//            log.error("Error while trying to retrieve user type: {}", e);
//            throw e;
//        }
//    }

    @Override
    public boolean getUserType(UserDto userDto) {
        try {
            boolean isApplicant = "applicant".equalsIgnoreCase(userDto.getAccountType());
            log.info("Is user an applicant: {}", isApplicant);
            return isApplicant;
        } catch (Exception e) {
            log.error("Error while trying to retrieve user type: {}", e);
            throw e;
        }
    }

    protected UserDto toDto(User user) {
        return UserDto.builder().id(user.getId()).name(user.getName()).surname(user.getSurname())
                .email(user.getEmail()).password(user.getPassword()).age(user.getAge()).avatar(user.getAvatar())
                .accountType(user.getAccountType()).enabled(user.isEnabled()).build();
    }

    protected static User fromDto(UserDto userDto) {
        return User.builder()
                .name(userDto.getName()).
                surname(userDto.getSurname()).
                email(userDto.getEmail()).password(userDto.getPassword())
                .age(userDto.getAge()).avatar(userDto.getAvatar()).accountType(userDto.getAccountType()).enabled(userDto.isEnabled()).build();
    }
}
