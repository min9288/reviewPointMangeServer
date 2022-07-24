package com.reviewPointMangeServer.domain.controller.place;

import com.reviewPointMangeServer.domain.dto.requestDTO.place.PlaceAddRequestDto;
import com.reviewPointMangeServer.domain.dto.responseDTO.place.PlaceAddResponseDto;
import com.reviewPointMangeServer.domain.dto.responseDTO.place.PlaceFindAllResponseDto;
import com.reviewPointMangeServer.domain.service.place.PlaceService;
import com.reviewPointMangeServer.domain.service.response.ResponseService;
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
