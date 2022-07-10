package com.tripleCMS.tripleCMS.dto.responseDto.place;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class PlaceAddResponseDto {
    private UUID placeId;
    private String placeName;

    @Builder
    public PlaceAddResponseDto(UUID placeId, String placeName) {
        this.placeId = placeId;
        this.placeName = placeName;
    }
}
