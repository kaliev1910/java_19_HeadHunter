package com.example.java_19_headhunter.service.interfaces;

import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface MailService {

    void sendEmail(String toEmail, String link) throws MessagingException, UnsupportedEncodingException;
}
