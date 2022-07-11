package com.tripleCMS.tripleCMS.repository;

import com.tripleCMS.tripleCMS.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findAllByUserId(UUID userId);
}
