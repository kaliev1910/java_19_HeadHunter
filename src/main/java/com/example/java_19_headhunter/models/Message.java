package com.example.java_19_headhunter.models;

import lombok.Data;

import java.util.Date;
@Data
public class Message {
    private long id;
    private User responsedUser;
    private Vacancy vacancy;
    protected Date responseDate;
}
