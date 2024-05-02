package com.example.java_19_headhunter.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User {
    private int id;
    private String name;
    private String surname;
    private byte age;
    @Id
    private String email;
    private String password;
    private String avatar;
    private String accountType;
    private boolean enabled;

}
