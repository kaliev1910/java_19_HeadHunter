package com.example.java_19_headhunter.enums;

public enum AccountType {
    APPLICANT("Applicant"), EMPLOYER("Employer");

    private final String value;

    AccountType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}