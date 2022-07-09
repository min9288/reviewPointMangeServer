package com.tripleCMS.tripleCMS.service;

import com.tripleCMS.tripleCMS.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@NoArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReviewService {
    private ReviewRepository reviewRepository;

    // 리뷰 출력

}
