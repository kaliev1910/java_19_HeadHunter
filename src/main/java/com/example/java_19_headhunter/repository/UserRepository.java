package com.example.java_19_headhunter.repository;

import com.example.java_19_headhunter.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String > {

    User findUserByEmail(String name);
}
