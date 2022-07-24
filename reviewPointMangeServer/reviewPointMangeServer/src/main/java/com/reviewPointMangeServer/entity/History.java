package com.reviewPointMangeServer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.reviewPointMangeServer.entity.Review;
import com.reviewPointMangeServer.entity.enumPackage.Action;
import com.reviewPointMangeServer.entity.enumPackage.Event;
import com.reviewPointMangeServer.entity.Place;
import com.reviewPointMangeServer.entity.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "history")
public class History {

    // 이력 번호
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long historyNo;

    // 이벤트 타입
    @Enumerated(EnumType.STRING)
    private Event type;

    // 액션
    @Enumerated(EnumType.STRING)
    private Action action;

    // 이벤트 발생시 증감 포인트
    @Column(nullable = false)
    private int transactionPoint;

    // 증감된 후 보유 포인트
    @Column(nullable = false)
    private int totalPoint;

    // 유저 UUID
    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    // 리뷰 UUID
    @Column(columnDefinition = "BINARY(16)")
    private UUID reviewId;

    // 장소 UUID
    @Column(columnDefinition = "BINARY(16)")
    private UUID placeId;

    // 이벤트 발생일시
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @CreationTimestamp
    private Timestamp eventEnrollDate;


    @Builder History(Event type, Action action, int transactionPoint, int totalPoint, User user, Review review, Place place) {
        this.type = type;
        this.action = action;
        this.transactionPoint = transactionPoint;
        this.totalPoint = totalPoint;
        this.userId = user.getUserId();
        this.reviewId = review.getReviewId();
        this.placeId = place.getPlaceId();
    }

//    // user 테이블 참조
//    @ManyToOne
//    @JoinColumn(name = "userId")
//    private User user;
//
//    // review 테이블 참조
//    @ManyToOne
//    @JoinColumn(name = "reviewId")
//    private Review review;
}
