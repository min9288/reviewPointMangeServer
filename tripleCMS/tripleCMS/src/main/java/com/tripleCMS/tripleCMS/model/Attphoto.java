package com.tripleCMS.tripleCMS.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="attphoto")
public class Attphoto {

    // 사진 UUID
    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID photoUUID;

    // 사진 파일명
    @Column(nullable = false, length = 300)
    private String photoName;

    @ManyToOne
    @JoinColumn(name="reviewUUID")
    private Review review;

}
