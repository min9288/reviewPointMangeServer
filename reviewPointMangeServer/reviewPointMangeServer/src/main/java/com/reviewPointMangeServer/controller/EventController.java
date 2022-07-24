package com.reviewPointMangeServer.controller;

import com.reviewPointMangeServer.dto.requestDTO.event.EventReqeustDto;
import com.reviewPointMangeServer.dto.responseDTO.events.EventResponseDto;
import com.reviewPointMangeServer.service.EventService;
import com.reviewPointMangeServer.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.SingleResult;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/events")
public class EventController {

    private final ResponseService responseService;
    private final EventService eventService;


    @PostMapping("")
    public SingleResult<EventResponseDto> selectEventService(@RequestBody EventReqeustDto reqeustDto) {
           EventResponseDto responseDto = eventService.EventServies(reqeustDto);
           return responseService.getSingleResult(responseDto);
    }



}
