package com.tripleCMS.tripleCMS.controller;

import com.tripleCMS.tripleCMS.dto.requestDto.events.EventReqeustDto;
import com.tripleCMS.tripleCMS.dto.responseDto.events.EventResponseDto;
import com.tripleCMS.tripleCMS.model.History;
import com.tripleCMS.tripleCMS.model.enumPackage.Event;
import com.tripleCMS.tripleCMS.service.EventService;
import com.tripleCMS.tripleCMS.service.ResponseService;
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
