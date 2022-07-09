package com.tripleCMS.tripleCMS.dto.responseDto.place;

import com.tripleCMS.tripleCMS.model.Place;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class PlaceFindAllResponseDto {
    private UUID placeUUID;
    private String placeName;
    private int placeReviewCount;

    public static PlaceFindAllResponseDto createDto(Place place) {
        return PlaceFindAllResponseDto.builder()
                .placeUUID(place.getPlaceUUID())
                .placeName(place.getPlaceName())
                .placeReviewCount(place.getPlaceReviewCount())
                .build();
    }

    @Builder
    public PlaceFindAllResponseDto(UUID placeUUID, String placeName, int placeReviewCount) {
        this.placeUUID = placeUUID;
        this.placeName = placeName;
        this.placeReviewCount = placeReviewCount;
    }
}
