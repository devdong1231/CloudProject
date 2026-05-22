package com.cloudproject.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateMemberResponse {
    private final Long id;
    private final String name;
    private final int age;
    private final String mbti;
}
