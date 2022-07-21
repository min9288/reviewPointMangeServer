package com.reviewPointMangeServer.reviewPointMangeServer.place.controller;

import com.reviewPointMangeServer.reviewPointMangeServer.place.dto.requestDTO.PlaceAddRequestDto;
import com.reviewPointMangeServer.reviewPointMangeServer.place.dto.responseDTO.PlaceAddResponseDto;
import com.reviewPointMangeServer.reviewPointMangeServer.place.dto.responseDTO.PlaceFindAllResponseDto;
import com.reviewPointMangeServer.reviewPointMangeServer.place.service.PlaceService;
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
