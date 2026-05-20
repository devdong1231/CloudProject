package com.cloudproject.member;

import com.cloudproject.member.dto.CreateMemberRequest;
import com.cloudproject.member.dto.CreateMemberResponse;
import com.cloudproject.member.dto.GetMemberResponse;
import com.cloudproject.member.dto.ProfileImageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<CreateMemberResponse> addMember(@Valid @RequestBody CreateMemberRequest request) {
        CreateMemberResponse result = memberService.addMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMemberResponse> getMember(@PathVariable Long id) {
        GetMemberResponse result = memberService.getOneMember(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/{id}/profile-image")
    public ResponseEntity<Void> uploadProfileImage(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile image
    ) {
        memberService.uploadProfileImage(id, image);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/profile-image")
    public ResponseEntity<ProfileImageResponse> getProfileImage(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(memberService.getProfileImage(id));
    }
}
