package com.reviewPointMangeServer.domain.dto.responseDTO.place;

import com.reviewPointMangeServer.domain.entity.place.Place;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class PlaceFindAllResponseDto {
    private UUID placeId;
    private String placeName;
    private int placeReviewCount;

    public static PlaceFindAllResponseDto createDto(Place place) {
        return PlaceFindAllResponseDto.builder()
                .placeId(place.getPlaceId())
                .placeName(place.getPlaceName())
                .placeReviewCount(place.getPlaceReviewCount())
                .build();
    }

    @Builder
    public PlaceFindAllResponseDto(UUID placeId, String placeName, int placeReviewCount) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.placeReviewCount = placeReviewCount;
    }
}
