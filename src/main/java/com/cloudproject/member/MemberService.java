package com.cloudproject.member;

import com.cloudproject.common.S3Service;
import com.cloudproject.common.exception.NotFoundException;
import com.cloudproject.member.dto.CreateMemberRequest;
import com.cloudproject.member.dto.CreateMemberResponse;
import com.cloudproject.member.dto.GetMemberResponse;
import com.cloudproject.member.dto.ProfileImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final S3Service s3Service;

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

    @Transactional
    public void uploadProfileImage(Long id, MultipartFile image) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        String imageKey = s3Service.uploadProfileImage(id, image);

        member.updateProfileImg(imageKey);
    }

    public ProfileImageResponse getProfileImage(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        String presignedUrl = s3Service.createPresignedUrl(member.getProfileImgKey());

        return new ProfileImageResponse(presignedUrl);
    }

}
