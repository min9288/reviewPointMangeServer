package com.tripleCMS.tripleCMS.repository;

import com.tripleCMS.tripleCMS.model.Attphoto;
import com.tripleCMS.tripleCMS.model.Review;
import com.tripleCMS.tripleCMS.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

    Optional<Review> findReviewByReviewId(UUID reviewId);

    Optional<Review> findReviewByUserIdAndPlaceId(UUID UserId, UUID PlaceId);

    Optional<Review> findByUserId(UUID userId);

    List<Review> findAllByUserId(UUID userId);

//    @Query("select r.userId from Review r where r.placeId = :placeId and r.userId = :userId")
//    Optional<Review> findByWriter(@Param("userId") UUID userId, @Param("placeId") UUID placeID);

//    @Query("select r.userId from Review r where r.placeId = :placeId and r.userId = :userId")
//    Optional<Review> findReviewByUserIdAndPlaceId(@Param("userId") UUID userId, @Param("placeId") UUID placeID);


}
