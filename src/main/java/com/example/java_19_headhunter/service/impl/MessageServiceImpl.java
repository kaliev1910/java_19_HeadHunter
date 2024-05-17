package com.example.java_19_headhunter.service.impl;

import com.example.java_19_headhunter.dto.basicDtos.MessageDto;
import com.example.java_19_headhunter.models.Message;
import com.example.java_19_headhunter.repository.MessageRepository;
import com.example.java_19_headhunter.service.interfaces.MessageService;
import com.example.java_19_headhunter.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository chatRepository;
    private final UserService userService;


}
