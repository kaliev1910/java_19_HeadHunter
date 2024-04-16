package com.example.java_19_headhunter.enums;

import lombok.Getter;

@Getter
public enum AccountType {
    APPLICANT("APPLICANT"), EMPLOYER("EMPLOYER");

    private final String value;

    AccountType(String value) {
        this.value = value;
    }

}