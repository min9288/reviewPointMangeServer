package com.reviewPointMangeServer.dto.requestDTO.review;

import com.reviewPointMangeServer.entity.enumPackage.Action;
import com.reviewPointMangeServer.entity.enumPackage.Event;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ReviewUpdateRequestDto {
    private Event type;
    private Action action;
    private UUID reviewId;
    private String content;
    private String attachedPhotoIds;
    private UUID userId;
    private UUID placeId;

//    @Builder
//    public ReviewUpdateRequestDto(Event type, Action action, UUID reviewId, String content
//            , String attachedPhotoIds, UUID userId, UUID placeId) {
//        this.type = type;
//        this.action = action;
//        this.reviewId = reviewId;
//        this.content = content;
//        this.attachedPhotoIds = attachedPhotoIds;
//        this.userId = userId;
//        this.placeId = placeId;
//    }
}
