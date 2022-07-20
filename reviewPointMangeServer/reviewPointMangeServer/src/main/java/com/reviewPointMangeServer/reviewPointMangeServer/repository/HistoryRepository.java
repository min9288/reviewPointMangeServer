package com.reviewPointMangeServer.reviewPointMangeServer.repository;

import com.reviewPointMangeServer.reviewPointMangeServer.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findAllByUserId(UUID userId);

    @Transactional
    @Query("select sum(h.transactionPoint) from History h where h.reviewId = :reviewId")
    Integer findByTransactionPoint(@Param("reviewId") UUID reviewId);

//    public Integer sumTransactionPoint(@Param("reviewId") UUID reviewId);

//    Integer findByTransactionPoint(@Param("reviewId") UUID reviewId);


}
