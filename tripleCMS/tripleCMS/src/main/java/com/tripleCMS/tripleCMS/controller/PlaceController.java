package com.tripleCMS.tripleCMS.controller;

import com.tripleCMS.tripleCMS.dto.requestDto.place.PlaceRequestDto;
import com.tripleCMS.tripleCMS.dto.responseDto.place.PlaceResponseDto;
import com.tripleCMS.tripleCMS.service.PlaceService;
import com.tripleCMS.tripleCMS.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.SingleResult;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/place")
public class PlaceController {

    private final PlaceService placeService;
    private final ResponseService responseService;

    @PostMapping("/add")
    public SingleResult<PlaceResponseDto> addPlace(@RequestBody PlaceRequestDto requestDto) {
        PlaceResponseDto responseDto = placeService.addPlace(requestDto);
        return responseService.getSingleResult(responseDto);
    }
}
