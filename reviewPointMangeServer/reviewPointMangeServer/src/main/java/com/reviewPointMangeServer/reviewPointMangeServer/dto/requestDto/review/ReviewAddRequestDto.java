package com.reviewPointMangeServer.reviewPointMangeServer.dto.requestDto.review;

import com.reviewPointMangeServer.reviewPointMangeServer.model.Place;
import com.reviewPointMangeServer.reviewPointMangeServer.model.User;
import com.reviewPointMangeServer.reviewPointMangeServer.model.enumPackage.Action;
import com.reviewPointMangeServer.reviewPointMangeServer.model.enumPackage.Event;
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
