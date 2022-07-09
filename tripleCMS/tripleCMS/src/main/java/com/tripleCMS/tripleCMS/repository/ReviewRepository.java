package com.tripleCMS.tripleCMS.repository;

import com.tripleCMS.tripleCMS.model.Review;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;
import java.util.UUID;

@Mapper
public interface ReviewRepository {

    // 리뷰 출력
    @Select("select * from review where reviewuuid = #{reviewUUid}")
    public List<Review> findByReviewUUID(UUID reviewUUid);

    // 리뷰 작성
//    @Insert("inset into review()")

}
