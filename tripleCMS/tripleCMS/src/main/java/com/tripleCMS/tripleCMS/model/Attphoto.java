package com.tripleCMS.tripleCMS.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="attphoto")
public class Attphoto {

    // 사진 UUID
    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID photoId;

    // 사진 파일명
    @Column(nullable = false, length = 300)
    private String photoName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="reviewId")
    private Review review;

    @Builder
    public Attphoto(String photoName, Review review) {
        this.photoName = photoName;
        this.review = review;
    }

}
