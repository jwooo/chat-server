package com.chatserver.application.chatroom;

import com.chatserver.domain.chatroom.ChatRoom;
import com.chatserver.domain.chatroom.ChatRoomRepository;
import com.chatserver.domain.chatroom.MemberChatRoom;
import com.chatserver.domain.chatroom.MemberChatRoomRepository;
import com.chatserver.domain.member.Member;
import com.chatserver.domain.member.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomService {

    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MemberChatRoomRepository memberChatRoomRepository;

    public List<ChatRoomResponse> findChatRooms() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();

        return chatRooms.stream()
                .map(ChatRoomResponse::of)
                .toList();
    }

    @Transactional
    public ChatRoomResponse createChatRoom(Long userId) {
        Member findMember = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));

        ChatRoom savedChatRoom = chatRoomRepository.save(ChatRoom.create());
        memberChatRoomRepository.save(MemberChatRoom.of(findMember, savedChatRoom));

        return ChatRoomResponse.of(savedChatRoom);
    }

    @Transactional
    public ChatRoomResponse joinChatRoom(Long chatRoomId, Long userId) {
        Member requestMember = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));

        ChatRoom findChatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방입니다."));

        if (memberChatRoomRepository.existsByChatRoomAndMember(findChatRoom, requestMember)) {
            throw new IllegalArgumentException("이미 참여중인 채팅방 입니다.");
        }

        memberChatRoomRepository.save(MemberChatRoom.of(requestMember, findChatRoom));

        return ChatRoomResponse.of(findChatRoom);
    }


}
