package com.reviewPointMangeServer.reviewPointMangeServer.dto.requestDto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDto {
    private String userName;
    private String userPw;
}