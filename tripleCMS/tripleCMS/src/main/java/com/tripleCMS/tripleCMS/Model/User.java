package com.tripleCMS.tripleCMS.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userNo;
    private UUID userUUID;
    private String userId;
    private String userPw;
    private int point;
    private Date userEnrollDate;
}
