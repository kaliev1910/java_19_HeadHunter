package com.example.java_19_headhunter.dao.implementation;

import com.example.java_19_headhunter.dao.UserDao;
import com.example.java_19_headhunter.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao {
    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public User findByFullName(String firstName, String lastName) {
        return null;
    }

    @Override
    public boolean userExists(String email) {
        return false;
    }
}
