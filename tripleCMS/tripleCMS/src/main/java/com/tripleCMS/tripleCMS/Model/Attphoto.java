package com.tripleCMS.tripleCMS.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attphoto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int photoNo;
    private UUID reviewUUID;
    private String photoName;
    private UUID photoUUID;
}
