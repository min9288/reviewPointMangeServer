package com.reviewPointMangeServer.reviewPointMangeServer.history.dto.responseDTO;

import com.reviewPointMangeServer.reviewPointMangeServer.history.model.History;
import com.reviewPointMangeServer.reviewPointMangeServer.model.enumPackage.Action;
import com.reviewPointMangeServer.reviewPointMangeServer.model.enumPackage.Event;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class HistoryResponseDto {

    private long historyNo;
    private Event type;
    private Action action;
    private UUID userId;
    private int transactionPoint;
    private int totalPoint;
    private UUID reviewId;
    private UUID placeId;

    public static HistoryResponseDto createDto(History history) {
        return HistoryResponseDto.builder()
                .historyNo(history.getHistoryNo())
                .type(history.getType())
                .action(history.getAction())
                .userId(history.getUserId())
                .transactionPoint(history.getTransactionPoint())
                .totalPoint(history.getTotalPoint())
                .reviewId(history.getReviewId())
                .placeId(history.getPlaceId())
                .build();
    }

    @Builder
    public HistoryResponseDto(long historyNo, Event type, Action action, UUID userId, int transactionPoint
            , int totalPoint, UUID reviewId, UUID placeId) {
        this.historyNo = historyNo;
        this.type = type;
        this.action = action;
        this.userId = userId;
        this.transactionPoint = transactionPoint;
        this.totalPoint = totalPoint;
        this.reviewId = reviewId;
        this.placeId = placeId;
    }
}
