package com.tripleCMS.tripleCMS.dto.requestDto.review;

import com.tripleCMS.tripleCMS.model.Attphoto;
import com.tripleCMS.tripleCMS.model.Place;
import com.tripleCMS.tripleCMS.model.User;
import com.tripleCMS.tripleCMS.model.enumPackage.Action;
import com.tripleCMS.tripleCMS.model.enumPackage.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


public class ReviewRequestDto {

    @Data
    @AllArgsConstructor
    public static class addReview {
        private Event event;
        private Action action;
        private String reviewText;
        private User user;
        private Place place;
        private List<Attphoto> attphotos;
    }

}
