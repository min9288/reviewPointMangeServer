package com.tripleCMS.tripleCMS.dto.responseDto.review;

import com.tripleCMS.tripleCMS.model.Attphoto;
import com.tripleCMS.tripleCMS.model.enumPackage.Action;
import com.tripleCMS.tripleCMS.model.enumPackage.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ReviewAddResponseDto {
    private Event type;
    private Action action;
    private UUID reviewId;
    private String content;
    private List<Attphoto> attachedPhotoIds = new ArrayList<>();
    private UUID userId;
    private UUID placeId;

    @Builder
    public ReviewAddResponseDto(Event type, Action action, UUID reviewId, String content
    , List<Attphoto> attachedPhotoIds, UUID userId, UUID placeId) {
        this.type = type;
        this.action = action;
        this.reviewId = reviewId;
        this.content = content;
        this.attachedPhotoIds = attachedPhotoIds;
        this.userId = userId;
        this.placeId = placeId;
    }
}
