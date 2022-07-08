package com.tripleCMS.tripleCMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponseDto {
    private UUID userUUID;
    private String token;
    private String refreshToken;
}
