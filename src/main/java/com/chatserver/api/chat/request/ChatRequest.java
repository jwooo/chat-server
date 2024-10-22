package com.chatserver.api.chat.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRequest {

    private String email;
    private String nickname;
    private String roomCode;
    private String message;

    @Builder
    private ChatRequest(String email, String nickname, String roomCode, String message) {
        this.email = email;
        this.nickname = nickname;
        this.roomCode = roomCode;
        this.message = message;
    }

    public static ChatRequest of(String email, String nickname, String roomCode, String message) {
        return ChatRequest.builder()
                .email(email)
                .nickname(nickname)
                .roomCode(roomCode)
                .message(message)
                .build();
    }

}
