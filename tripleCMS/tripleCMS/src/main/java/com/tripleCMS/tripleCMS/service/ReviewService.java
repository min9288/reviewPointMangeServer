package com.tripleCMS.tripleCMS.service;

import com.tripleCMS.tripleCMS.config.S3Uploader;
import com.tripleCMS.tripleCMS.dto.requestDto.review.ReviewAddRequestDto;
import com.tripleCMS.tripleCMS.dto.responseDto.review.ReviewAddResponseDto;
import com.tripleCMS.tripleCMS.exception.PlaceAlreadyExistsException;
import com.tripleCMS.tripleCMS.exception.PlaceNotFoundException;
import com.tripleCMS.tripleCMS.exception.WriterAlreadyExistsException;
import com.tripleCMS.tripleCMS.model.Attphoto;
import com.tripleCMS.tripleCMS.model.Place;
import com.tripleCMS.tripleCMS.model.Review;
import com.tripleCMS.tripleCMS.repository.PhotoRepository;
import com.tripleCMS.tripleCMS.repository.PlaceRepository;
import com.tripleCMS.tripleCMS.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PhotoRepository photoRepository;

    private final PlaceRepository placeRepository;
    private final S3Service s3Service;

    // DTO로 들어온 값을 통해 리뷰 등록
    @Transactional
    public ReviewAddResponseDto addReview(ReviewAddRequestDto requestDto) {
        validateDuplicated(requestDto.getUserId(), requestDto.getPlaceId());
        Review review = reviewRepository.save(
                Review.builder()
                        .type(requestDto.getType())
                        .action(requestDto.getAction())
                        .content(requestDto.getContent())
                        .userId(requestDto.getUserId())
                        .placeId(requestDto.getPlaceId())
                        .build());

        Place place = findByPlace(requestDto.getPlaceId());
        int placeTotalReview = place.getPlaceReviewCount();
        placeTotalReview += 1;
        place.setPlaceReviewCount(placeTotalReview);
        placeRepository.save(place);

        return ReviewAddResponseDto.builder()
                .type(review.getType())
                .action(review.getAction())
                .reviewId(review.getReviewId())
                .content(review.getContent())
                .attachedPhotoIds(requestDto.getAttachedPhotoIds())
                .userId(review.getUserId())
                .placeId(review.getPlaceId())
                .build();
    }

    @Transactional
    public ReviewAddResponseDto addReview(ReviewAddRequestDto requestDto, List<MultipartFile> multipartFiles) {
        validateDuplicated(requestDto.getUserId(), requestDto.getPlaceId());
        List<String> imageList =  s3Service.uploadFile(multipartFiles);
        Review review = reviewRepository.save(
                Review.builder()
                        .type(requestDto.getType())
                        .action(requestDto.getAction())
                        .content(requestDto.getContent())
                        .userId(requestDto.getUserId())
                        .placeId(requestDto.getPlaceId())
                        .build());
        for (String image : imageList) {
            Attphoto attphoto = photoRepository.save(
                    Attphoto.builder()
                            .attachedPhotoIds(image)
                            .review(review)
                            .build());
        }

        Place place = findByPlace(requestDto.getPlaceId());
        int placeTotalReview = place.getPlaceReviewCount();
        placeTotalReview += 1;
        place.setPlaceReviewCount(placeTotalReview);
        placeRepository.save(place);

        return ReviewAddResponseDto.builder()
                .type(review.getType())
                .action(review.getAction())
                .reviewId(review.getReviewId())
                .content(review.getContent())
                .attachedPhotoIds(imageList)
                .userId(review.getUserId())
                .placeId(review.getPlaceId())
                .build();
    }

    public void validateDuplicated(UUID userId, UUID placeId) {
        if(reviewRepository.findByWriter(userId, placeId).isPresent()) throw new WriterAlreadyExistsException();
    }

    private Place findByPlace(UUID placeId) {
        return placeRepository.findByPlaceId(placeId).orElseThrow(PlaceNotFoundException::new);
    }

}
