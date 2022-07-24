package com.reviewPointMangeServer.domain.dto.requestDTO.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequestDto {
    private String userName;
    private String userPw;
}
