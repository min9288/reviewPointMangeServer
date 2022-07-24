package com.reviewPointMangeServer.domain.dto.requestDTO.review;

import com.reviewPointMangeServer.domain.entity.enumPackage.Action;
import com.reviewPointMangeServer.domain.entity.enumPackage.Event;
import com.reviewPointMangeServer.domain.entity.place.Place;
import com.reviewPointMangeServer.domain.entity.user.User;
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
