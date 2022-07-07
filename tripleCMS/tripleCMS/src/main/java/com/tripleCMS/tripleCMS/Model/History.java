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
public class History {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hNo;
    private UUID userUUID;
    private UUID reviewUUID;
    private String eventType;
    private int pointChange;
    private int pointCalCount;
    private Date eventEnrollDate;
}
