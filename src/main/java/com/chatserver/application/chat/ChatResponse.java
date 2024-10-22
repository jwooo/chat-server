package com.chatserver.application.chat;

import com.chatserver.api.chat.request.ChatRequest;
import com.chatserver.domain.chat.Chat;
import com.chatserver.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatResponse {

    private String email;
    private String nickname;
    private String roomCode;
    private String message;

    @Builder
    private ChatResponse(String email, String nickname, String roomCode, String message) {
        this.email = email;
        this.nickname = nickname;
        this.roomCode = roomCode;
        this.message = message;
    }

    public static ChatResponse of(String email, String nickname, String roomCode, String message) {
        return ChatResponse.builder()
                .email(email)
                .nickname(nickname)
                .roomCode(roomCode)
                .message(message)
                .build();
    }

    public static ChatResponse of(Chat chat) {
        return ChatResponse.builder()
                .email(chat.getEmail())
                .nickname(chat.getNickname())
                .roomCode(chat.getRoomCode())
                .message(chat.getMessage())
                .build();
    }

}
