package com.tripleCMS.tripleCMS.dto.requestDto.events;

import com.tripleCMS.tripleCMS.model.Attphoto;
import com.tripleCMS.tripleCMS.model.Place;
import com.tripleCMS.tripleCMS.model.User;
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
public class EventAddReqeustDto {
    private Event type;
    private Action action;
    private String content;
    private List<Attphoto> attachedPhotoIds = new ArrayList<>();
    private UUID userId;
    private UUID placeId;



}
