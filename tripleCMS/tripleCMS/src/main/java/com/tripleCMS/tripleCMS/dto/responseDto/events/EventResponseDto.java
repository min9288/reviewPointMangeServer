package com.tripleCMS.tripleCMS.dto.responseDto.events;

import com.tripleCMS.tripleCMS.model.Attphoto;
import com.tripleCMS.tripleCMS.model.History;
import com.tripleCMS.tripleCMS.model.Place;
import com.tripleCMS.tripleCMS.model.User;
import com.tripleCMS.tripleCMS.model.enumPackage.Action;
import com.tripleCMS.tripleCMS.model.enumPackage.Event;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class EventResponseDto {
    private Action action;
    private UUID reviewId;
    private UUID placeId;
    private UUID userId;
    private int transactionPoint ;
    private int totalPoint;


    @Builder
    public EventResponseDto(Action action, int transactionPoint, int totalPoint, UUID userId, UUID placeId, UUID reviewId) {
        this.action = action;
        this.transactionPoint = transactionPoint;
        this.totalPoint = totalPoint;
        this.userId = userId;
        this.placeId = placeId;
        this.reviewId = reviewId;
    }
}
