package com.ilearn.controller;

import com.ilearn.domain.dto.ChatMessage;
import com.ilearn.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/i-learn")
public class MessagesController {
    @Autowired
    ChatService chatService;

    @RequestMapping(method = RequestMethod.GET, value = "/messages/all")
    public List<ChatMessage> getSchedule() {
        List<ChatMessage> messages = new ArrayList<>();
        chatService.getMessages().subscribe(messages::add);
        return messages;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/messages/create", consumes = APPLICATION_JSON_VALUE)
    public void createStudent(@RequestBody ChatMessage message) {
        chatService.getPublisher().onNext(message);
    }
}
