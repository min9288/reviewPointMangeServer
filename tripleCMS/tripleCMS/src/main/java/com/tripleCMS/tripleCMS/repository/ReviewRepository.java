package com.tripleCMS.tripleCMS.repository;

import com.tripleCMS.tripleCMS.model.Review;
import com.tripleCMS.tripleCMS.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

//    Optional<Review> findByUserId(UUID userId);
    Optional<Review> findByUserId(UUID userId);

    // 리뷰 출력
//    @Select("select * from review where reviewuuid = #{reviewUUid}")
//    public List<Review> findByReviewUUID(UUID reviewUUid);

    // 리뷰 작성
//    @Insert("inset into review()")

}
