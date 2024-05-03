package com.example.java_19_headhunter.repository;

import com.example.java_19_headhunter.models.User;
import com.example.java_19_headhunter.models.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserImageRepository  extends JpaRepository<UserImage, Integer> {
    Optional<UserImage> findByUserId_Id(long userId);

    void deleteByUserId_Id(long userId);

    UserImage findByImageId(long imageId);
}
