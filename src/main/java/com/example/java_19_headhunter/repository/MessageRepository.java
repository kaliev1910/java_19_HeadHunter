package com.example.java_19_headhunter.repository;

import com.example.java_19_headhunter.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findBySender_Email(String sender);
}
