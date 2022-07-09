package com.tripleCMS.tripleCMS.service;

import com.tripleCMS.tripleCMS.dto.requestDto.place.PlaceAddRequestDto;
import com.tripleCMS.tripleCMS.dto.responseDto.place.PlaceAddResponseDto;
import com.tripleCMS.tripleCMS.dto.responseDto.place.PlaceFindAllResponseDto;
import com.tripleCMS.tripleCMS.exception.PlaceAlreadyExistsException;
import com.tripleCMS.tripleCMS.model.Place;
import com.tripleCMS.tripleCMS.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    // DTO로 들어온 값을 통해 장소 등록
    @Transactional
    public PlaceAddResponseDto addPlace(PlaceAddRequestDto requestDto) {
        validateDuplicated(requestDto.getPlaceName());
        Place place = placeRepository.save(
                Place.builder()
                        .placeName(requestDto.getPlaceName())
                        .build());
        return PlaceAddResponseDto.builder()
                .placeName(place.getPlaceName())
                .build();
    }

    public void validateDuplicated(String placeName) {
        if(placeRepository.findByPlaceName(placeName).isPresent()) throw new PlaceAlreadyExistsException();
    }

    public List<PlaceFindAllResponseDto> findAllPlace() {
        List<Place> places = placeRepository.findAll();
        return places.stream()
                .map(place -> PlaceFindAllResponseDto.createDto(place))
                .collect(Collectors.toList());
    }
}
