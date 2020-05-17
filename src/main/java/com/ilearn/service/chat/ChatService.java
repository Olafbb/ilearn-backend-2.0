package com.ilearn.service.chat;

import com.ilearn.domain.dto.ChatMessage;
import lombok.Getter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

@Service
@Getter
public class ChatService {
    private final UnicastProcessor<ChatMessage> publisher;
    private final Flux<ChatMessage> messages;

    public ChatService(UnicastProcessor<ChatMessage> publisher, Flux<ChatMessage> messages) {
        this.publisher = publisher;
        this.messages = messages;
    }
}
