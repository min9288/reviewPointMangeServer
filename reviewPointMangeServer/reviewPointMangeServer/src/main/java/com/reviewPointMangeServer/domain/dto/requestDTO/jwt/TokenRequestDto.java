package com.reviewPointMangeServer.domain.dto.requestDTO.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenRequestDto {
    String accessToken;
    String refreshToken;
}
