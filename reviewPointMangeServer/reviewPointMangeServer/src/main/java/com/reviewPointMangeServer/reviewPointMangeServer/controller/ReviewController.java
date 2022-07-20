package com.reviewPointMangeServer.reviewPointMangeServer.controller;

import com.reviewPointMangeServer.reviewPointMangeServer.dto.requestDto.review.ReviewAddRequestDto;
import com.reviewPointMangeServer.reviewPointMangeServer.dto.requestDto.review.ReviewUpdateRequestDto;
import com.reviewPointMangeServer.reviewPointMangeServer.dto.responseDto.review.ReviewDeleteResponseDto;
import com.reviewPointMangeServer.reviewPointMangeServer.dto.responseDto.review.ReviewGetResponseDto;
import com.reviewPointMangeServer.reviewPointMangeServer.dto.responseDto.review.ReviewResponseDto;
import com.reviewPointMangeServer.reviewPointMangeServer.dto.responseDto.review.ReviewUpdateResponseDto;
import com.reviewPointMangeServer.reviewPointMangeServer.service.ResponseService;
import com.reviewPointMangeServer.reviewPointMangeServer.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import result.MultipleResult;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {
    private final ResponseService responseService;
    private final ReviewService reviewService;
    // 사진이 포함된 리뷰
    @PostMapping(value = "/addAttPhoto", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ReviewResponseDto addReview(@Valid @RequestPart ReviewAddRequestDto requestDto, @RequestPart List<MultipartFile> multipartFiles) {
        ReviewResponseDto responseDto = reviewService.addReview(requestDto, multipartFiles);
        return responseDto;
    }

    // 글만 있는 리뷰
    @PostMapping("/add")
    public ReviewResponseDto addReview(@Valid @RequestBody ReviewAddRequestDto requestDto) {
        ReviewResponseDto responseDto = reviewService.addReview(requestDto);
        return responseDto;
    }

    @GetMapping("/{userId}")
    public MultipleResult<ReviewGetResponseDto> findMyReview(@PathVariable("userId") UUID userId) {
        return responseService.getMultipleResult(reviewService.getUserReview(userId));
    }

    @DeleteMapping("/{reviewId}")
    public ReviewDeleteResponseDto deleteReview(@PathVariable("reviewId") UUID reviewId) {
        ReviewDeleteResponseDto responseDto = reviewService.deleteReview(reviewId);
        return responseDto;
    }

    @PutMapping("/update")
    public ReviewUpdateResponseDto updateReview(@RequestBody ReviewUpdateRequestDto requestDto) {
        ReviewUpdateResponseDto responseDto = reviewService.updateReview(requestDto);
        return  responseDto;
    }

//    @DeleteMapping("/delete")
//    public ReviewResponseDto deleteReview(@RequestBody )

//    @PostMapping("/test")
//    public UserLoginResponseDto test(@RequestBody UserLoginResponseDto responseDto) {
//        return responseDto;
//    }
}
