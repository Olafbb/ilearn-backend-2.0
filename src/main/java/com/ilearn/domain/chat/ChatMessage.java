package com.ilearn.domain.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ChatMessage {
    private String from;
    private LocalDateTime time;
    private String message;
}
