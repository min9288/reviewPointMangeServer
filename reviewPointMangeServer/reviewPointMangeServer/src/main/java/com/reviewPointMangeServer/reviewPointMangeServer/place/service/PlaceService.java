package com.reviewPointMangeServer.reviewPointMangeServer.place.service;

import com.reviewPointMangeServer.reviewPointMangeServer.place.dto.responseDTO.PlaceFindAllResponseDto;
import com.reviewPointMangeServer.reviewPointMangeServer.place.model.Place;
import com.reviewPointMangeServer.reviewPointMangeServer.place.dto.requestDTO.PlaceAddRequestDto;
import com.reviewPointMangeServer.reviewPointMangeServer.place.dto.responseDTO.PlaceAddResponseDto;
import com.reviewPointMangeServer.reviewPointMangeServer.exception.PlaceAlreadyExistsException;
import com.reviewPointMangeServer.reviewPointMangeServer.place.repository.PlaceRepository;
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
