package com.tripleCMS.tripleCMS.controller;

import com.tripleCMS.tripleCMS.dto.requestDto.review.ReviewAddRequestDto;
import com.tripleCMS.tripleCMS.dto.responseDto.review.ReviewAddResponseDto;
import com.tripleCMS.tripleCMS.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;
    // 사진이 포함된 리뷰
    @PostMapping(value = "/addAttPhoto", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ReviewAddResponseDto addReview(@RequestPart ReviewAddRequestDto requestDto, @RequestPart List<MultipartFile> multipartFiles) {
        ReviewAddResponseDto responseDto = reviewService.addReview(requestDto, multipartFiles);
        return responseDto;
    }

    // 글만 있는 리뷰
    @PostMapping("/add")
    public ReviewAddResponseDto addReview(@RequestBody ReviewAddRequestDto requestDto) {
        ReviewAddResponseDto responseDto = reviewService.addReview(requestDto);
        return responseDto;
    }

//    @PostMapping("/test")
//    public UserLoginResponseDto test(@RequestBody UserLoginResponseDto responseDto) {
//        return responseDto;
//    }
}
