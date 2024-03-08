package com.example.java_19_headhunter.models;

import com.example.java_19_headhunter.enums.UserType;
import lombok.Data;

@Data
public class User {
    private long id;
    private String username;
    private String email;
    private UserType userType;
    private String password;

}
