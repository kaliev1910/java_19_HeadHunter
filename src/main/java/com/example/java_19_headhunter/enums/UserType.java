package com.example.java_19_headhunter.enums;

public enum UserType {
    APPLICANT("Applicant"), EMPLOYER("Employer");

    private final String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}