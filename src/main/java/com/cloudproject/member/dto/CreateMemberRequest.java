package com.cloudproject.member.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class CreateMemberRequest {
    @NotNull(message = "이름은 필수입니다.")
    private String name;

    @Min(value = 1, message = "나이는 1살 미만일 수 없습니다.")
    @Max(value = 100, message = "나이는 100살을 넘길 수 없습니다.")
    @NotNull(message = "나이는 필수 입니다.")
    private int age;

    @Pattern(
            regexp = "^(ISTJ|ISFJ|INFJ|INTJ|ISTP|ISFP|INFP|INTP|ESTP|ESFP|ENFP|ENTP|ESTJ|ESFJ|ENFJ|ENTJ)$",
            message = "올바른 MBTI 형식이 아닙니다."
    )
    @NotNull(message = "mbti는 필수 입니다.")
    private String mbti;
}
