package com.tripleCMS.tripleCMS.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "history")
public class History {

    // 이력 번호
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int historyNo;

    // 이벤트 타입
    @Column(nullable = false, length = 20)
    private String eventType;

    // 이벤트 발생시 증감 포인트
    @Column(nullable = false)
    private int pointChange;

    // 증감된 후 보유 포인트
    @Column(nullable = false)
    private int pointCalCount;

    // 이벤트 발생일시
    @CreationTimestamp
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private Timestamp eventEnrollDate;

    // user 테이블 참조
    @ManyToOne
    @JoinColumn(name = "userUUID")
    private User user;

    // review 테이블 참조
    @ManyToOne
    @JoinColumn(name = "reviewUUID")
    private Review review;
}
