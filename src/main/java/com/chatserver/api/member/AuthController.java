package com.chatserver.api.member;

import com.chatserver.api.ApiResponse;
import com.chatserver.api.member.request.MemberLoginRequest;
import com.chatserver.api.member.request.MemberRegisterRequest;
import com.chatserver.api.support.SessionUser;
import com.chatserver.application.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
    private final HttpSession session;

    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody MemberRegisterRequest request) {
        memberService.register(request.getEmail(), request.getPassword(), request.getNickname());

        return ApiResponse.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody MemberLoginRequest request) {
        SessionUser sessionUser = SessionUser.of(memberService.login(request.getEmail(), request.getPassword()));

        session.setAttribute("JSESSIONID", sessionUser);

        return ApiResponse.ok("로그인 성공");
    }

}
