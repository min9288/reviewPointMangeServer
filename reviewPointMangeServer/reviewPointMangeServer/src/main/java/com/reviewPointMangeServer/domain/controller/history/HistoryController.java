package com.reviewPointMangeServer.domain.controller.history;

import com.reviewPointMangeServer.domain.service.history.HistoryService;
import com.reviewPointMangeServer.domain.service.response.ResponseService;
import com.reviewPointMangeServer.domain.dto.responseDTO.history.HistoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.reviewPointMangeServer.domain.result.MultipleResult;

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
