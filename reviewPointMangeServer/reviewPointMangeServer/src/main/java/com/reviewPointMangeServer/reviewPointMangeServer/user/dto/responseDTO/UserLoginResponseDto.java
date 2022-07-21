package com.reviewPointMangeServer.reviewPointMangeServer.user.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponseDto {
    private UUID userID;
    private String token;
    private String refreshToken;
}
