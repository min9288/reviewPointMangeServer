package com.tripleCMS.tripleCMS.dto.requestDto.review;

import com.tripleCMS.tripleCMS.model.Attphoto;
import com.tripleCMS.tripleCMS.model.Place;
import com.tripleCMS.tripleCMS.model.User;
import com.tripleCMS.tripleCMS.model.enumPackage.Action;
import com.tripleCMS.tripleCMS.model.enumPackage.Event;
import lombok.AllArgsConstructor;
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

    public ReviewAddRequestDto(User  user, Place  place) {
        this.userId = user.getUserId();
        this.placeId = place.getPlaceId();
    }

}
