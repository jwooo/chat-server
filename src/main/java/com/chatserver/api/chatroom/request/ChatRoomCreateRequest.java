package com.chatserver.api.chatroom.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoomCreateRequest {

    private String roomName;

}
