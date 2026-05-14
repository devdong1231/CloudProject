package com.cloudproject.member;

import com.cloudproject.member.dto.CreateMemberRequest;
import com.cloudproject.member.dto.CreateMemberResponse;
import com.cloudproject.member.dto.GetMemberResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<GetMemberResponse> getMember(@PathVariable Long id){
        GetMemberResponse result = memberService.getOneMember(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
