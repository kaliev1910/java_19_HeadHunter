package com.example.java_19_headhunter.exeptions;

import java.util.NoSuchElementException;

public class VacancyNotFoundException extends NoSuchElementException {
    public VacancyNotFoundException(String s) {
        super(s);
    }
}
