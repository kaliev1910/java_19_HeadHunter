package com.example.java_19_headhunter.exeptions;

import java.util.NoSuchElementException;

public class ResumeNotFoundException extends NoSuchElementException {
    public ResumeNotFoundException(String s) {
        super(s);
    }
}
