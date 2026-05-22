package com.cloudproject.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String mbti;

    @Column(nullable = false)
    private String profileImgUrl;

    @Column(nullable = false)
    private String profileImgKey;

    public void updateProfileImg(String profileImgUrl, String profileImgKey) {
        this.profileImgKey = profileImgKey;
        this.profileImgUrl = profileImgUrl;
    }

    @Builder
    public Member(String name, int age, String mbti) {
        this.name = name;
        this.age = age;
        this.mbti = mbti;
    }

}
