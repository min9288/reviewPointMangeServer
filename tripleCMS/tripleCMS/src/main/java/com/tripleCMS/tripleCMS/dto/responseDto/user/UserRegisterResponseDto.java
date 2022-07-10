package com.tripleCMS.tripleCMS.dto.responseDto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserRegisterResponseDto {
    private String userName;

    private String userPw;

    @Builder
    public UserRegisterResponseDto(String userName, String userPw) {
        this.userPw = userPw;
        this.userName = userName;
    }

}
