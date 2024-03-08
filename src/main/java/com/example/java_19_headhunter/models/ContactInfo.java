package com.example.java_19_headhunter.models;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class ContactInfo {
    private long id;
    private long userId;
    private String telegram;
    private String email;
    private Integer phoneNumber;
    private String facebook;
    private String linkedin;


}