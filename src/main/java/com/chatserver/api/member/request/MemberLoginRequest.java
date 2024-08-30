package com.chatserver.api.member.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberLoginRequest {

    private String email;
    private String password;

    @Builder
    private MemberLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
