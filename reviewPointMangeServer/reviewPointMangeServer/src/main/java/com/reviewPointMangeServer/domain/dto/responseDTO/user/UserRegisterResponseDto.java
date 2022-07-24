package com.reviewPointMangeServer.domain.dto.responseDTO.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
