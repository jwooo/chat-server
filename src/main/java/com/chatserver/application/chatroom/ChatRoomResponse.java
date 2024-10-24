package com.chatserver.application.chatroom;

import com.chatserver.domain.chatroom.ChatRoom;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoomResponse {

    private Long chatRoomId;
    private String chatRoomCode;

    @Builder
    private ChatRoomResponse(Long chatRoomId, String chatRoomCode) {
        this.chatRoomId = chatRoomId;
        this.chatRoomCode = chatRoomCode;
    }

    public static ChatRoomResponse of(ChatRoom chatRoom) {
        return ChatRoomResponse.builder()
                .chatRoomId(chatRoom.getId())
                .chatRoomCode(chatRoom.getRoomCode())
                .build();
    }

}
