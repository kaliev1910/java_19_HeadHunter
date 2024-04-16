package com.example.java_19_headhunter.models;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    private int id;
    @Email
    private String sender;
    private String content;
    private Timestamp timestamp;
}
