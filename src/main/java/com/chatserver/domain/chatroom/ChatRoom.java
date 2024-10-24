package com.chatserver.domain.chatroom;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Getter;

@Entity
@Getter
public class ChatRoom {

    @Id
    @GeneratedValue
    @Column(name = "chat_room_id")
    private Long id;

    private String roomCode;

    protected ChatRoom() {
        this.roomCode = UUID.randomUUID().toString();
    }

    public static ChatRoom create() {
        return new ChatRoom();
    }

}
