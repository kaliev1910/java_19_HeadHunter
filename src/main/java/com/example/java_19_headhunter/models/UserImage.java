package com.example.java_19_headhunter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserImage {
    private long userId;
    private int imageId;
    private String fileName;

}
