package com.example.java_19_headhunter.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Email
    @ManyToOne
    @JoinColumn(name = "sender_email")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver_email")
    private User reciever;
    private String content;
    private Timestamp timestamp;
}
