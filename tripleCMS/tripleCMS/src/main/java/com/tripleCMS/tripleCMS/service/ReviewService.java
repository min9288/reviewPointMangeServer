package com.tripleCMS.tripleCMS.service;

import com.tripleCMS.tripleCMS.config.S3Uploader;
import com.tripleCMS.tripleCMS.dto.requestDto.review.ReviewAddRequestDto;
import com.tripleCMS.tripleCMS.dto.responseDto.review.ReviewAddResponseDto;
import com.tripleCMS.tripleCMS.exception.PlaceAlreadyExistsException;
import com.tripleCMS.tripleCMS.exception.WriterAlreadyExistsException;
import com.tripleCMS.tripleCMS.model.Review;
import com.tripleCMS.tripleCMS.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // DTO로 들어온 값을 통해 리뷰 등록
    @Transactional
    public ReviewAddResponseDto addReview(ReviewAddRequestDto requestDto) {
        validateDuplicated(requestDto.getUserId());
        Review review = reviewRepository.save(
                Review.builder()
                        .type(requestDto.getType())
                        .action(requestDto.getAction())
                        .content(requestDto.getContent())
                        .attachedPhotoIds(requestDto.getAttachedPhotoIds())
                        .userId(requestDto.getUserId())
                        .placeId(requestDto.getPlaceId())
                        .build());
        return ReviewAddResponseDto.builder()
                .type(review.getType())
                .action(review.getAction())
                .reviewId(review.getReviewId())
                .content(review.getContent())
                .attachedPhotoIds(review.getAttachedPhotoIds())
                .userId(review.getUserId())
                .placeId(review.getPlaceId())
                .build();
    }

    public ReviewAddResponseDto addReview(ReviewAddRequestDto requestDto, List<MultipartFile> multipartFiles) {
        validateDuplicated(requestDto.getUserId());
        Review review = reviewRepository.save(
                Review.builder()
                        .type(requestDto.getType())
                        .action(requestDto.getAction())
                        .content(requestDto.getContent())
                        .attachedPhotoIds(requestDto.getAttachedPhotoIds())
                        .userId(requestDto.getUserId())
                        .placeId(requestDto.getPlaceId())
                        .build());
        return ReviewAddResponseDto.builder()
                .type(review.getType())
                .action(review.getAction())
                .reviewId(review.getReviewId())
                .content(review.getContent())
                .attachedPhotoIds(review.getAttachedPhotoIds())
                .userId(review.getUserId())
                .placeId(review.getPlaceId())
                .build();
    }

    public void validateDuplicated(UUID userId) {
        if(reviewRepository.findByUserId(userId).isPresent()) throw new WriterAlreadyExistsException();
    }

}
