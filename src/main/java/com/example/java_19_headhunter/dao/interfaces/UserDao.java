package com.example.java_19_headhunter.dao.interfaces;

import com.example.java_19_headhunter.models.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component

public interface UserDao {
    public List<User> getUsers();

    public void updateUser(User user);

    public void createUser(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByName(String name);

    boolean userExists(String email);

    boolean getUserType(User user);
}
