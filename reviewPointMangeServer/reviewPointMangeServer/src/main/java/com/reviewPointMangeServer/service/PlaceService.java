package com.reviewPointMangeServer.service;

import com.reviewPointMangeServer.exception.PlaceAlreadyExistsException;
import com.reviewPointMangeServer.entity.Place;
import com.reviewPointMangeServer.repository.PlaceRepository;
import com.reviewPointMangeServer.dto.responseDTO.place.PlaceFindAllResponseDto;
import com.reviewPointMangeServer.dto.requestDTO.place.PlaceAddRequestDto;
import com.reviewPointMangeServer.dto.responseDTO.place.PlaceAddResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
                .placeId(place.getPlaceId())
                .placeName(place.getPlaceName())
                .build();
    }


    public void validateDuplicated(String placeName) {
        if(placeRepository.findByPlaceName(placeName).isPresent()) throw new PlaceAlreadyExistsException();
    }

    @Transactional(readOnly = true)
    public List<PlaceFindAllResponseDto> findAllPlace() {
        List<Place> places = placeRepository.findAll();
        return places.stream()
                .map(place -> PlaceFindAllResponseDto.createDto(place))
                .collect(Collectors.toList());
    }
}
