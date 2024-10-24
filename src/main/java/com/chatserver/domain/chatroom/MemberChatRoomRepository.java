package com.chatserver.domain.chatroom;

import com.chatserver.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberChatRoomRepository extends JpaRepository<MemberChatRoom, Long> {

    boolean existsByChatRoomAndMember(ChatRoom chatRoom, Member member);

}
