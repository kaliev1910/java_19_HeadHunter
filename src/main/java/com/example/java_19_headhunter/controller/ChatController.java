package com.example.java_19_headhunter.controller;

import com.example.java_19_headhunter.models.Message;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor

public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(Message chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Message addUser(Message chatMessage) {
        return chatMessage;
    }
}
