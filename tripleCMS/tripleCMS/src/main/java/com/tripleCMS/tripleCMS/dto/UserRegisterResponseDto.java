package com.tripleCMS.tripleCMS.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserRegisterResponseDto {
//    private UUID userUUID;
    private String userId;

    private String userPw;

    @Builder
    public UserRegisterResponseDto(String userId, String userPw) {
        this.userPw = userPw;
        this.userId = userId;
    }

}
