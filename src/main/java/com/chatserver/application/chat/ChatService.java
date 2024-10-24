package com.chatserver.application.chat;

import com.chatserver.api.chat.request.ChatRequest;
import com.chatserver.domain.chat.Chat;
import com.chatserver.domain.chat.ChatRepository;
import com.chatserver.domain.member.Member;
import com.chatserver.domain.member.MemberRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {

    private final MemberRepository memberRepository;
    private final ChatRepository chatRepository;

    public ChatResponse createSessionAttribute(ChatRequest request, SimpMessageHeaderAccessor headerAccessor) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        try {
            Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("email", request.getEmail());
            headerAccessor.getSessionAttributes().put("roomCode", request.getRoomCode());
            headerAccessor.getSessionAttributes().put("nickname", request.getNickname());
        } catch (Exception e) {
            throw new IllegalArgumentException("세션 연결 중 문제가 발생하였습니다.");
        }

        Chat chat = chatRepository.save(Chat.ofNew(request));

        return ChatResponse.of(chat);
    }

    @Transactional
    public ChatResponse send(ChatRequest chatRequest) {
        Member member = memberRepository.findByEmail(chatRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Chat chat = chatRepository.save(Chat.of(chatRequest));

        return ChatResponse.of(chat);
    }

}
