package com.reviewPointMangeServer.dto.responseDTO.review;


import com.reviewPointMangeServer.entity.Review;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ReviewGetResponseDto {

    private UUID reviewId;
    private String content;
    private String attachedPhotoIds;
    private UUID userId;
    private UUID placeId;

    public static ReviewGetResponseDto createDto(Review review) {
        return ReviewGetResponseDto.builder()
                .reviewId(review.getReviewId())
                .content(review.getContent())
                .attachedPhotoIds(review.getPhotoList())
                .userId(review.getUserId())
                .placeId(review.getPlaceId())
                .build();
    }

    @Builder ReviewGetResponseDto(UUID reviewId, String content, String attachedPhotoIds, UUID userId, UUID placeId) {
        this.reviewId = reviewId;
        this.content = content;
        this.attachedPhotoIds = attachedPhotoIds;
        this.userId = userId;
        this.placeId = placeId;

    }
}
