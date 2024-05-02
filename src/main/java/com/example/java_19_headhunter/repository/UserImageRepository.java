package com.example.java_19_headhunter.repository;

import com.example.java_19_headhunter.models.User;
import com.example.java_19_headhunter.models.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserImageRepository  extends JpaRepository<UserImage, Integer> {
    @Query("select u from UserImage u where u.userId = :userId")
    User findByUserId(long userId);
}
