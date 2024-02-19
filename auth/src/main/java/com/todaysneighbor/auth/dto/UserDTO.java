package com.todaysneighbor.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private long providerId;
    private String nickname;
    private String filename; // 프로필 이미지 파일명
    private float rating = 0; // 초기 평점은 0으로 설정
}
