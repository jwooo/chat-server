package com.chatserver.api.support;

import com.chatserver.application.member.MemberResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SessionUser {

    private Long id;
    private String email;
    private String nickname;

    @Builder
    private SessionUser(Long id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }

    public static SessionUser of(MemberResponse member) {
        return SessionUser.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }

}
