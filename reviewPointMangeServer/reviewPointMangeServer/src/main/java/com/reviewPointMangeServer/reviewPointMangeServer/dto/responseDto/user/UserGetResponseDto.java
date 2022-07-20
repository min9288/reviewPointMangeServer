package com.reviewPointMangeServer.reviewPointMangeServer.dto.responseDto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserGetResponseDto {
    private UUID userId;
    private String userName;
    private int point;
    private int userLevel;

    @Builder
    public UserGetResponseDto(UUID userId, String userName, int point, int userLevel){
        this.userId = userId;
        this.userName = userName;
        this.point = point;
        this.userLevel = userLevel;
    }
}
