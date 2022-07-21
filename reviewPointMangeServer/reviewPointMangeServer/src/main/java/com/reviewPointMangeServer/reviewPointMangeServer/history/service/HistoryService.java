package com.reviewPointMangeServer.reviewPointMangeServer.history.service;

import com.reviewPointMangeServer.reviewPointMangeServer.history.dto.responseDTO.HistoryResponseDto;
import com.reviewPointMangeServer.reviewPointMangeServer.history.model.History;
import com.reviewPointMangeServer.reviewPointMangeServer.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;

    @Transactional(readOnly = true)
    public List<HistoryResponseDto> getUserHistory(UUID userId) {
        List<History> historyList = historyRepository.findAllByUserId(userId);
        return historyList.stream()
                .map(history -> HistoryResponseDto.createDto(history))
                .collect(Collectors.toList());
    }

}
