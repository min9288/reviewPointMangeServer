package com.tripleCMS.tripleCMS.service;

import com.tripleCMS.tripleCMS.dto.requestDto.events.EventReqeustDto;
import com.tripleCMS.tripleCMS.dto.responseDto.events.EventResponseDto;
import com.tripleCMS.tripleCMS.exception.PlaceNotFoundException;
import com.tripleCMS.tripleCMS.exception.UserNotFoundException;
import com.tripleCMS.tripleCMS.model.History;
import com.tripleCMS.tripleCMS.model.Place;
import com.tripleCMS.tripleCMS.model.User;
import com.tripleCMS.tripleCMS.model.enumPackage.Action;
import com.tripleCMS.tripleCMS.repository.HistoryRepository;
import com.tripleCMS.tripleCMS.repository.PlaceRepository;
import com.tripleCMS.tripleCMS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {

    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final HistoryRepository historyRepository;


    public EventResponseDto EventServies(EventReqeustDto reqeustDto) {
        Action action = reqeustDto.getAction();
        EventResponseDto eventResponseDto = null;
        switch (action) {
            case ADD:
                eventResponseDto = addEventService(reqeustDto);
                break;
            case MOD:
//                eventResponseDto = modEventService(reqeustDto);
                break;
            case DELETE:
//                eventResponseDto = deleteEventService(reqeustDto);
                break;
        }
        return eventResponseDto;
    }

    @Transactional
    public EventResponseDto addEventService(EventReqeustDto reqeustDto) {

        User user = findUserById(reqeustDto.getUserId()); // 리뷰작성 유저
        int userPoint = user.getPoint(); // 유저 보유 포인트
        int userLevel = user.getUserLevel(); // 유저 현재 레벨

        int countSavePoint = 0; // 서비스로 인한 포인트 증가치

        Place place = findByPlace(reqeustDto.getPlaceId()); // 리뷰 작성 장소
        int placeReviewCount = place.getPlaceReviewCount(); // 장소에 작성된 리뷰 갯수

        // 작성 내용별 포인트 적립 프로세스
        // 리뷰 텍스트가 1자 이상이고 사진 첨부 안했을때 -> 1점 적립
        if(reqeustDto.getContent().length() >= 1 && reqeustDto.getAttachedPhotoIds().isEmpty()) {
            userPoint += 1;
            user.setPoint(userPoint);
            countSavePoint += 1;
        }
        // 리뷰 텍스트가 1자 이상 + 사진 첨부 했을때 -> 2점 적립
        else if (reqeustDto.getContent().length() >= 1 && !(reqeustDto.getAttachedPhotoIds().isEmpty())) {
            userPoint += 2;
            user.setPoint(userPoint);
            countSavePoint += 2;
        }
        // 장소 최초 리뷰 일대 -> 1점 추가 적립
        if (placeReviewCount == 0) {
            userPoint += 1;
            user.setPoint(userPoint);
            countSavePoint += 1;
        }
        if(5 <= userPoint ||  userPoint <= 7 ){
            user.setUserLevel(1);
        } else if (40 <= userPoint || userPoint <= 42) {
            user.setUserLevel(2);
        } else if (100 <= userPoint || userPoint <= 102) {
            user.setUserLevel(3);
        }
        userRepository.save(user);

        // 이력 등록 프로세스
        History history = historyRepository.save(
                History.builder()
                        .type(reqeustDto.getType())
                        .action(reqeustDto.getAction())
                        .transactionPoint(countSavePoint)
                        .totalPoint(userPoint)
                        .userId(reqeustDto.getUserId())
                        .placeId(reqeustDto.getPlaceId())
                        .reviewId(reqeustDto.getReviewId())
                        .build());
        return EventResponseDto.builder()
                .action(reqeustDto.getAction())
                .userId(reqeustDto.getUserId())
                .placeId(reqeustDto.getPlaceId())
                .reviewId(reqeustDto.getReviewId())
                .transactionPoint(countSavePoint)
                .totalPoint(userPoint)
                .build();

    }

//    public EventResponseDto modEventService(EventReqeustDto reqeustDto) {
//
//    }

//    public EventResponseDto deleteEventService(EventReqeustDto reqeustDto) {
//
//    }

    private User findUserById(UUID userId) {
        return userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);
    }

    private Place findByPlace(UUID placeId) {
        return placeRepository.findByPlaceId(placeId).orElseThrow(PlaceNotFoundException::new);
    }


}
