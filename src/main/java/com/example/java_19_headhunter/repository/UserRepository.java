package com.example.java_19_headhunter.repository;

import com.example.java_19_headhunter.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("select m from User m where m.id = :id")
    User getUserById(Integer   id);

    Optional<User> findUserByEmail(String name);

    Optional<User> findUserByName(String name);

    Boolean existsByEmail(String email);
}
