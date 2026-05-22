package com.cloudproject.member;

import com.cloudproject.common.exception.NotFoundException;
import com.cloudproject.member.dto.CreateMemberRequest;
import com.cloudproject.member.dto.CreateMemberResponse;
import com.cloudproject.member.dto.GetMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public CreateMemberResponse addMember(CreateMemberRequest request) {
        Member member = Member.builder()
                .name(request.getName())
                .age(request.getAge())
                .mbti(request.getMbti())
                .build();

        memberRepository.save(member);

        return CreateMemberResponse.builder()
                .id(member.getId())
                .name(request.getName())
                .age(request.getAge())
                .mbti(request.getMbti()).build();
    }

    @Transactional(readOnly = true)
    public GetMemberResponse getOneMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new NotFoundException("해당 팀원을 찾을 수 없습니다.")
        );

        return GetMemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .age(member.getAge())
                .mbti(member.getMbti()).build();
    }

}
