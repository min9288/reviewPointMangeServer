package com.tripleCMS.tripleCMS.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewNo;
    private UUID memberUUID;
    private UUID placeUUID;
    private UUID reviewUUID;
    private String reviewTitle;
    private String reviewContent;
    private int photoCount;
    private Date reviewEnrollDate;
}
