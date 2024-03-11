package com.example.java_19_headhunter.models;

import lombok.Builder;

@Builder
public class Experince {
    private int id;
    private int resumeId;
    private String companyName;
    private String position;
    private String responsibilities;
    private int years;

}
