package com.tripleCMS.tripleCMS.dto.responseDto.events;

import com.tripleCMS.tripleCMS.model.Attphoto;
import com.tripleCMS.tripleCMS.model.Place;
import com.tripleCMS.tripleCMS.model.User;
import com.tripleCMS.tripleCMS.model.enumPackage.Action;
import com.tripleCMS.tripleCMS.model.enumPackage.Event;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class EventAddResponseDto {
    private Event event;
    private Action action;
    private UUID reviewId;
    private String content;
    private List<Attphoto> attachedPhotoIds = new ArrayList<>();
    private UUID userId;
    private UUID placeId;

    public EventAddResponseDto(User user, Place place) {
        this.userId = user.getUserId();
        this.placeId = place.getPlaceId();
    }
}
