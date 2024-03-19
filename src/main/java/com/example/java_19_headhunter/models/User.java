package com.example.java_19_headhunter.models;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    private int id;
    private String name;
    private String surname;
    private byte age;
    private String email;
    private String password;
    private String avatar;
    private String accountType;

}
