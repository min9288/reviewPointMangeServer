package com.reviewPointMangeServer.domain.entity.review;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.reviewPointMangeServer.domain.entity.photo.Attphoto;
import com.reviewPointMangeServer.domain.entity.place.Place;
import com.reviewPointMangeServer.domain.entity.enumPackage.Action;
import com.reviewPointMangeServer.domain.entity.enumPackage.Event;
import com.reviewPointMangeServer.domain.entity.user.User;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="review")
public class Review {

    // 리뷰 UUID
    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID reviewId;

    // 리뷰 텍스트
    @Column(nullable = false, length = 2000)
    private String content;

    // 리뷰에 첨부된 사진 수
    @ColumnDefault("0")
    private int photoCount;

    @ColumnDefault("0")
    private int savingPoint;

    // 리뷰 작성일
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @CreationTimestamp
    private Timestamp reviewEnrollDate;

    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(columnDefinition = "BINARY(16)")
    private UUID placeId;

    @Column(length = 2000)
    private String photoList;

////    user 테이블 참조
//    @ManyToOne
//    @JoinColumn(name = "userId")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private User user;
//
////     place 테이블 참조
//    @ManyToOne
//    @JoinColumn(name = "placeId")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private Place place;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Attphoto> attachedPhotoIds = new ArrayList<>();

    @Transient
    @Enumerated(EnumType.STRING)
    private Event type;

    @Transient
    @Enumerated(EnumType.STRING)
    private Action action;

    @Builder Review(User user, Place place) {
        this.userId = user.getUserId();
        this.placeId = place.getPlaceId();
    }



}
