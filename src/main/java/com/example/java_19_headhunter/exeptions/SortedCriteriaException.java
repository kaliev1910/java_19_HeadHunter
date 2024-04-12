package com.example.java_19_headhunter.exeptions;

import java.util.NoSuchElementException;

public class SortedCriteriaException extends NoSuchElementException {
    public SortedCriteriaException(String m) {
        super(m);
    }
}
