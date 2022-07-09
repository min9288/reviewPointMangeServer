package com.tripleCMS.tripleCMS.service;

import com.tripleCMS.tripleCMS.dto.requestDto.place.PlaceRequestDto;
import com.tripleCMS.tripleCMS.dto.responseDto.place.PlaceResponseDto;
import com.tripleCMS.tripleCMS.exception.PlaceAlreadyExistsException;
import com.tripleCMS.tripleCMS.exception.UserIdAlreadyExistsException;
import com.tripleCMS.tripleCMS.model.Place;
import com.tripleCMS.tripleCMS.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    // DTO로 들어온 값을 통해 장소 등록
    @Transactional
    public PlaceResponseDto addPlace(PlaceRequestDto requestDto) {
        validateDuplicated(requestDto.getPlaceName());
        Place place = placeRepository.save(
                Place.builder()
                        .placeName(requestDto.getPlaceName())
                        .build());
        return PlaceResponseDto.builder()
                .placeName(place.getPlaceName())
                .build();
    }

    public void validateDuplicated(String placeName) {
        if(placeRepository.findByPlaceName(placeName).isPresent()) throw new PlaceAlreadyExistsException();
    }
}
