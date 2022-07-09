package com.tripleCMS.tripleCMS.controller;

import com.tripleCMS.tripleCMS.dto.requestDto.place.PlaceAddRequestDto;
import com.tripleCMS.tripleCMS.dto.responseDto.place.PlaceAddResponseDto;
import com.tripleCMS.tripleCMS.dto.responseDto.place.PlaceFindAllResponseDto;
import com.tripleCMS.tripleCMS.service.PlaceService;
import com.tripleCMS.tripleCMS.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import result.MultipleResult;
import result.SingleResult;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/place")
public class PlaceController {

    private final PlaceService placeService;
    private final ResponseService responseService;

    @PostMapping("/add")
    public SingleResult<PlaceAddResponseDto> addPlace(@RequestBody PlaceAddRequestDto requestDto) {
        PlaceAddResponseDto responseDto = placeService.addPlace(requestDto);
        return responseService.getSingleResult(responseDto);
    }

    @GetMapping("/")
    public MultipleResult<PlaceFindAllResponseDto> findAllPlace() {
        return responseService.getMultipleResult(placeService.findAllPlace());
    }
}
