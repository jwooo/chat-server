package com.chatserver.domain.chatroom;

import com.chatserver.domain.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberChatRoom {

    @Id
    @GeneratedValue
    @Column(name = "member_chat_room_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @Builder
    private MemberChatRoom(Member member, ChatRoom chatRoom) {
        this.member = member;
        this.chatRoom = chatRoom;
    }

    public static MemberChatRoom of(Member member, ChatRoom chatRoom) {
        return MemberChatRoom.builder()
                .member(member)
                .chatRoom(chatRoom)
                .build();
    }

}
