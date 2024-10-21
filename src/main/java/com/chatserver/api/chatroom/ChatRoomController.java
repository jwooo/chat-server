package com.chatserver.api.chatroom;

import com.chatserver.api.ApiResponse;
import com.chatserver.api.support.Auth;
import com.chatserver.application.chatroom.ChatRoomResponse;
import com.chatserver.application.chatroom.ChatRoomService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/chat-rooms")
    public ApiResponse<List<ChatRoomResponse>> findChatRooms() {
        return ApiResponse.ok(chatRoomService.findChatRooms());
    }

    @PostMapping("/chat-rooms")
    public ApiResponse<ChatRoomResponse> createChatRoom(@Auth Long userId) {
        return ApiResponse.ok(chatRoomService.createChatRoom(userId));
    }

    @PostMapping("/chat-rooms/{chatRoomId}/join")
    public ApiResponse<ChatRoomResponse> joinChatRoom(@PathVariable(name = "chatRoomId") Long chatRoomId, @Auth Long userId) {
        return ApiResponse.ok(chatRoomService.joinChatRoom(chatRoomId, userId));
    }

}
