package com.reviewPointMangeServer.domain.entity.photo;

import com.reviewPointMangeServer.domain.entity.review.Review;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="attphoto")
public class Attphoto {

    // 사진 UUID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long photoNo;

    // 사진 파일명
    private String attachedPhotoIds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="reviewId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Review review;

    @Builder
    public Attphoto(String attachedPhotoIds, Review review) {
        this.attachedPhotoIds = attachedPhotoIds;
        this.review = review;
    }

}
