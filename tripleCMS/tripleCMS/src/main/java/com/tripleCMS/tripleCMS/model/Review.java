package com.tripleCMS.tripleCMS.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
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
    private UUID reviewUUID;

    // 리뷰 텍스트
    @Column(nullable = false, length = 2000)
    private String reviewText;

    // 리뷰에 첨부된 사진 수
    @ColumnDefault("0")
    private int photoCount;

    // 리뷰 작성일
    @CreationTimestamp
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private Timestamp reviewEnrollDate;

    // user 테이블 참조
    @ManyToOne
    @JoinColumn(name = "userUUID")
    private User user;

    // place 테이블 참조
    @ManyToOne
    @JoinColumn(name = "placeUUID")
    private Place place;

    @OneToMany(mappedBy = "review", fetch = FetchType.EAGER)
    private List<Attphoto> attphotos;


}
