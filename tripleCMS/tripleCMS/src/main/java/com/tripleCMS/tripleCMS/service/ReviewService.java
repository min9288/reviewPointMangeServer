package com.tripleCMS.tripleCMS.service;

import com.tripleCMS.tripleCMS.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private ReviewRepository reviewRepository;

    // 리뷰 생성


}
