package com.tripleCMS.tripleCMS.dto.responseDto.place;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class PlaceResponseDto {
    private UUID placeUUid;
    private String placeName;

    @Builder
    public PlaceResponseDto(UUID placeUUid, String placeName) {
        this.placeUUid = placeUUid;
        this.placeName = placeName;
    }
}
