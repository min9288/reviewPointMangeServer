package com.reviewPointMangeServer.controller;

import com.reviewPointMangeServer.dto.responseDTO.history.HistoryResponseDto;
import com.reviewPointMangeServer.service.HistoryService;
import com.reviewPointMangeServer.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.MultipleResult;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/history")
public class HistoryController {

    private final HistoryService historyService;
    private final ResponseService responseService;

    @GetMapping("/{userId}")
    public MultipleResult<HistoryResponseDto> findHistory(@PathVariable("userId") UUID userId) {
        return responseService.getMultipleResult(historyService.getUserHistory(userId));
    }

}
