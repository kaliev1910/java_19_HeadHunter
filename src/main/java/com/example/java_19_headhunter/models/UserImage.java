package com.example.java_19_headhunter.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserImage {
    private int id;
    private int userId;
    private int imageId;

}
