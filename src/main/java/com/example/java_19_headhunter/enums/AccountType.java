package com.example.java_19_headhunter.enums;

import com.example.java_19_headhunter.models.Role;
import lombok.Getter;

import java.util.Collection;
import java.util.List;

@Getter
public enum AccountType {
    APPLICANT("APPLICANT"),
    EMPLOYER("EMPLOYER");

    private final String value;

    AccountType(String value) {
        this.value = value;
    }

    public static List<String> getAccountTypes() {
        return List.of(APPLICANT.getValue(), EMPLOYER.getValue());
    }
}