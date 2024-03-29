package com.reviewPointMangeServer.domain.entity.place;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "place")
public class Place {

    // 장소 UUID
    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID placeId;

    // 장소 이름
    @Column(nullable = false, unique = true, length = 300)
    private String placeName;

    // 장소에 작성된 리뷰 수
    @ColumnDefault("0")
    private int placeReviewCount;

}
