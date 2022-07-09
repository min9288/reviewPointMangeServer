package com.tripleCMS.tripleCMS.dto.requestDto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDto {
    private String userId;
    private String userPw;
}
