package com.chatserver.api.chat;

import com.chatserver.api.chat.request.ChatRequest;
import com.chatserver.application.chat.ChatResponse;
import com.chatserver.application.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate template;

    @MessageMapping("/chat-rooms/enter")
    public void enterChatRoom(@RequestBody ChatRequest request, SimpMessageHeaderAccessor headerAccessor) {
        ChatResponse response = chatService.createSessionAttribute(request, headerAccessor);

        template.convertAndSend("/sub/chat-rooms/" + request.getRoomCode(), response);
    }

    @MessageMapping("/chat-rooms/send")
    public void sendChatRoom(@RequestBody ChatRequest request) {
        ChatResponse response = chatService.send(request);

        template.convertAndSend("/sub/chat-rooms/" + response.getRoomCode(), response);
    }

}
