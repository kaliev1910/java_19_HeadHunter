package com.example.java_19_headhunter.models;

import com.example.java_19_headhunter.enums.AccountType;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {

    private int id;
    private String name;
    private String surname;
    private byte age; // TINYINT maps to byte in Java
    private String email;
    private String password;
    private String avatar;
    private String accountType;

}
