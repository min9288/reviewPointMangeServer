package com.reviewPointMangeServer.service;

import com.reviewPointMangeServer.entity.History;
import com.reviewPointMangeServer.repository.HistoryRepository;
import com.reviewPointMangeServer.dto.responseDTO.history.HistoryResponseDto;
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
