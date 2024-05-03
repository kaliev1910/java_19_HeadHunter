package com.example.java_19_headhunter.exeptions;

import org.springframework.security.access.AccessDeniedException;

public class DeniedException  extends AccessDeniedException {
    public DeniedException(String msg) {
        super(msg);
    }
}
