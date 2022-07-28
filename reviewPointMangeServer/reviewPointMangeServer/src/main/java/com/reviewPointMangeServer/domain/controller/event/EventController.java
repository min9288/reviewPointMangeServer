package com.reviewPointMangeServer.domain.controller.event;

import com.reviewPointMangeServer.domain.dto.requestDTO.event.EventReqeustDto;
import com.reviewPointMangeServer.domain.dto.responseDTO.events.EventResponseDto;
import com.reviewPointMangeServer.domain.service.event.EventService;
import com.reviewPointMangeServer.domain.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.reviewPointMangeServer.domain.result.SingleResult;

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
