package com.reviewPointMangeServer.dto.requestDTO.review;

import com.reviewPointMangeServer.entity.enumPackage.Action;
import com.reviewPointMangeServer.entity.enumPackage.Event;
import com.reviewPointMangeServer.entity.Place;
import com.reviewPointMangeServer.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ReviewAddRequestDto {

    private Event type;
    private Action action;
    private String content;
    private UUID userId;
    private UUID placeId;
    private List<String> attachedPhotoIds;

    public ReviewAddRequestDto(User user, Place place) {
        this.userId = user.getUserId();
        this.placeId = place.getPlaceId();
    }

}
