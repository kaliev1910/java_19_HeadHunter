package com.example.java_19_headhunter.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data

public class UserDto {

    private int id;
    private String name;
    private String surname;
    private byte age;
    private String email;
    private String password;
    private String avatar;
    private String accountType;


}

