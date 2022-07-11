package com.tripleCMS.tripleCMS.controller;

import com.tripleCMS.tripleCMS.dto.requestDto.review.ReviewAddRequestDto;
import com.tripleCMS.tripleCMS.dto.requestDto.review.ReviewUpdateRequestDto;
import com.tripleCMS.tripleCMS.dto.responseDto.history.HistoryResponseDto;
import com.tripleCMS.tripleCMS.dto.responseDto.review.ReviewDeleteResponseDto;
import com.tripleCMS.tripleCMS.dto.responseDto.review.ReviewGetResponseDto;
import com.tripleCMS.tripleCMS.dto.responseDto.review.ReviewResponseDto;
import com.tripleCMS.tripleCMS.dto.responseDto.review.ReviewUpdateResponseDto;
import com.tripleCMS.tripleCMS.service.ResponseService;
import com.tripleCMS.tripleCMS.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import result.MultipleResult;
import result.SingleResult;

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
