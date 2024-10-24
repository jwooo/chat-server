package com.chatserver.domain.chat;

import com.chatserver.api.chat.request.ChatRequest;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "chat")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat {

    @Id
    private String id;

    private MessageType type;

    private String nickname;

    private String email;

    private String roomCode;

    private String message;

    @Builder
    private Chat(MessageType type, String nickname, String email, String roomCode, String message) {
        this.type = type;
        this.nickname = nickname;
        this.email = email;
        this.roomCode = roomCode;
        this.message = message;
    }

    public static Chat of(MessageType type, String nickname, String email, String roomCode, String message) {
        return Chat.builder()
                .type(type)
                .nickname(nickname)
                .email(email)
                .roomCode(roomCode)
                .message(message)
                .build();
    }

    public static Chat of(ChatRequest request) {
        return Chat.builder()
                .type(MessageType.TALK)
                .nickname(request.getNickname())
                .email(request.getEmail())
                .roomCode(request.getRoomCode())
                .message(request.getMessage())
                .build();
    }

    public static Chat ofNew(ChatRequest request) {
        return Chat.builder()
                .type(MessageType.ENTER)
                .nickname(request.getNickname())
                .email(request.getEmail())
                .roomCode(request.getRoomCode())
                .message(request.getNickname() + "님이 입장하셨습니다.")
                .build();
    }

}
