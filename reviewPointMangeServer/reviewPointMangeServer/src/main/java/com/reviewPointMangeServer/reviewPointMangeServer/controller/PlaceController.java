package com.reviewPointMangeServer.reviewPointMangeServer.controller;

import com.reviewPointMangeServer.reviewPointMangeServer.dto.requestDto.place.PlaceAddRequestDto;
import com.reviewPointMangeServer.reviewPointMangeServer.dto.responseDto.place.PlaceAddResponseDto;
import com.reviewPointMangeServer.reviewPointMangeServer.dto.responseDto.place.PlaceFindAllResponseDto;
import com.reviewPointMangeServer.reviewPointMangeServer.service.PlaceService;
import com.reviewPointMangeServer.reviewPointMangeServer.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import result.MultipleResult;
import result.SingleResult;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/place")
public class PlaceController {

    private final PlaceService placeService;
    private final ResponseService responseService;

    @PostMapping("/add")
    public SingleResult<PlaceAddResponseDto> addPlace(@Valid  @RequestBody PlaceAddRequestDto requestDto) {
        PlaceAddResponseDto responseDto = placeService.addPlace(requestDto);
        return responseService.getSingleResult(responseDto);
    }

    @GetMapping("")
    public MultipleResult<PlaceFindAllResponseDto> findAllPlace() {
        return responseService.getMultipleResult(placeService.findAllPlace());
    }
}
