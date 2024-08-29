package com.chatserver.application.member;

import com.chatserver.domain.member.Member;
import com.chatserver.domain.member.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponse login(String email, String password) {
        Member findMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보가 잘못 되었습니다."));

        if (!findMember.isLoginMember(password)) {
            throw new IllegalArgumentException("사용자 정보가 잘못 되었습니다.");
        }

        return MemberResponse.of(findMember);
    }

    @Transactional
    public void register(String email, String password, String nickname) {
        Optional<Member> existMember = memberRepository.findByEmail(email);

        if (existMember.isPresent()) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        }

        memberRepository.save(Member.of(email, password, nickname));
    }

}
