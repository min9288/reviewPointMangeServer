package com.tripleCMS.tripleCMS.dto.responseDto.place;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class PlaceAddResponseDto {
    private UUID placeUUID;
    private String placeName;

    @Builder
    public PlaceAddResponseDto(UUID placeUUID, String placeName) {
        this.placeUUID = placeUUID;
        this.placeName = placeName;
    }
}
