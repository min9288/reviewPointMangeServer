package com.tripleCMS.tripleCMS.service;

import com.tripleCMS.tripleCMS.dto.requestDto.review.ReviewAddRequestDto;
import com.tripleCMS.tripleCMS.dto.requestDto.review.ReviewUpdateRequestDto;
import com.tripleCMS.tripleCMS.dto.responseDto.history.HistoryResponseDto;
import com.tripleCMS.tripleCMS.dto.responseDto.review.ReviewDeleteResponseDto;
import com.tripleCMS.tripleCMS.dto.responseDto.review.ReviewGetResponseDto;
import com.tripleCMS.tripleCMS.dto.responseDto.review.ReviewResponseDto;
import com.tripleCMS.tripleCMS.dto.responseDto.review.ReviewUpdateResponseDto;
import com.tripleCMS.tripleCMS.exception.PlaceNotFoundException;
import com.tripleCMS.tripleCMS.exception.ReviewNotFoundException;
import com.tripleCMS.tripleCMS.exception.WriterAlreadyExistsException;
import com.tripleCMS.tripleCMS.model.Attphoto;
import com.tripleCMS.tripleCMS.model.History;
import com.tripleCMS.tripleCMS.model.Place;
import com.tripleCMS.tripleCMS.model.Review;
import com.tripleCMS.tripleCMS.model.enumPackage.Action;
import com.tripleCMS.tripleCMS.model.enumPackage.Event;
import com.tripleCMS.tripleCMS.repository.PhotoRepository;
import com.tripleCMS.tripleCMS.repository.PlaceRepository;
import com.tripleCMS.tripleCMS.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public ReviewResponseDto addReview(ReviewAddRequestDto requestDto) {
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
        int placeTotalReview = place.getPlaceReviewCount() + 1;
        place.setPlaceReviewCount(placeTotalReview);
        placeRepository.save(place);

        return ReviewResponseDto.builder()
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
    public ReviewResponseDto addReview(ReviewAddRequestDto requestDto, List<MultipartFile> multipartFiles) {
        validateDuplicated(requestDto.getUserId(), requestDto.getPlaceId());
        List<String> imageList =  s3Service.uploadFile(multipartFiles);
        int imageCount = imageList.size();
        String imageStrList = String.join(",", imageList);
        Review review = reviewRepository.save(
                Review.builder()
                        .type(requestDto.getType())
                        .action(requestDto.getAction())
                        .content(requestDto.getContent())
                        .photoCount(imageCount)
                        .userId(requestDto.getUserId())
                        .placeId(requestDto.getPlaceId())
                        .photoList(imageStrList)
                        .build());
        for (String image : imageList) {
            Attphoto attphoto = photoRepository.save(
                    Attphoto.builder()
                            .attachedPhotoIds(image)
                            .review(review)
                            .build());
        }

        Place place = findByPlace(requestDto.getPlaceId());
        int placeTotalReview = place.getPlaceReviewCount() + 1;
        place.setPlaceReviewCount(placeTotalReview);
        placeRepository.save(place);

        return ReviewResponseDto.builder()
                .type(review.getType())
                .action(review.getAction())
                .reviewId(review.getReviewId())
                .content(review.getContent())
                .attachedPhotoIds(imageList)
                .userId(review.getUserId())
                .placeId(review.getPlaceId())
                .build();
    }

    @Transactional(readOnly = true)
    public List<ReviewGetResponseDto> getUserReview(UUID userId) {
        List<Review> reviewList = reviewRepository.findAllByUserId(userId);
        return reviewList.stream()
                .map(review -> ReviewGetResponseDto.createDto(review))
                .collect(Collectors.toList());
    }

    @Transactional
    public ReviewDeleteResponseDto deleteReview(UUID reviewId) {
        Review review = findReviewByReviewId(reviewId);
        String temp = review.getPhotoList();
        List<String> imageList = Arrays.asList(temp.split(","));
        ReviewDeleteResponseDto responseDto = ReviewDeleteResponseDto.builder()
                .type(Event.REVIEW)
                .action(Action.DELETE)
                .reviewId(review.getReviewId())
                .content(review.getContent())
                .attachedPhotoIds(imageList)
                .userId(review.getUserId())
                .reviewId(review.getReviewId())
                .placeId(review.getPlaceId())
                .build();

        for(String image : imageList) {
            String jpg = ".jpg";
            String imageResultStr = image.concat(jpg);
            s3Service.deleteFileJPG(imageResultStr);
        }

        for(String image : imageList) {
            String png = ".png";
            String imageResultStr = image.concat(png);
            s3Service.deleteFilePNG(imageResultStr);
        }

        return responseDto;
    }

    @Transactional
    public ReviewUpdateResponseDto updateReview(ReviewUpdateRequestDto requestDto) {
        Review review = findReviewByReviewId(requestDto.getReviewId());
        List<String> imageList = new ArrayList<>();

        if(!(requestDto.getAttachedPhotoIds().equals("[]"))){
            String temp = requestDto.getAttachedPhotoIds();
            imageList = Arrays.asList(temp.split(","));
        }

        review.setContent(requestDto.getContent());
        review.setPhotoList(requestDto.getAttachedPhotoIds());
        reviewRepository.save(review);


       return  ReviewUpdateResponseDto.builder()
                .type(Event.REVIEW)
                .action(Action.MOD)
                .reviewId(review.getReviewId())
                .content(review.getContent())
                .attachedPhotoIds(imageList)
                .userId(review.getUserId())
                .reviewId(review.getReviewId())
                .placeId(review.getPlaceId())
                .build();
    }




    public void validateDuplicated(UUID userId, UUID placeId) {
        if(reviewRepository.findReviewByUserIdAndPlaceId(userId, placeId).isPresent()) throw new WriterAlreadyExistsException();
    }

//    public void funValidateDuplicated(UUID userId, UUID placeId) {
//        return reviewRepository.findByWriter(userId, placeId).isPresent()) throw new WriterAlreadyExistsException();
//    }

    private Place findByPlace(UUID placeId) {
        return placeRepository.findByPlaceId(placeId).orElseThrow(PlaceNotFoundException::new);
    }
    private Review findReviewByReviewId(UUID reviewId) {
        return reviewRepository.findReviewByReviewId(reviewId).orElseThrow(ReviewNotFoundException::new);
    }

    // s3 이미지 삭제 해줘야됨


}
