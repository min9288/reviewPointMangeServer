package com.reviewPointMangeServer.domain.controller.user;

import com.reviewPointMangeServer.domain.controller.review.ReviewController;
import com.reviewPointMangeServer.domain.service.user.SignService;
import com.reviewPointMangeServer.domain.dto.responseDTO.user.UserGetResponseDto;
import com.reviewPointMangeServer.domain.dto.requestDTO.jwt.TokenRequestDto;
import com.reviewPointMangeServer.domain.dto.requestDTO.user.UserLoginRequestDto;
import com.reviewPointMangeServer.domain.dto.requestDTO.user.UserRegisterRequestDto;
import com.reviewPointMangeServer.domain.dto.responseDTO.jwt.TokenResponseDto;
import com.reviewPointMangeServer.domain.dto.responseDTO.user.UserLoginResponseDto;
import com.reviewPointMangeServer.domain.dto.responseDTO.user.UserRegisterResponseDto;
import com.reviewPointMangeServer.domain.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import result.SingleResult;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/sign")
public class SingController {
    private final SignService signService;
    private final ResponseService responseService;

    private final ReviewController reviewController;

    @PostMapping("/register")
    public SingleResult<UserRegisterResponseDto> register(@Valid @RequestBody UserRegisterRequestDto requestDto) {
        UserRegisterResponseDto responseDto = signService.registerUser(requestDto);
        return responseService.getSingleResult(responseDto);
    }

    @PostMapping("/login")
    public SingleResult<UserLoginResponseDto> login(@Valid  @RequestBody UserLoginRequestDto requestDto) {
        UserLoginResponseDto responseDto = signService.loginUser(requestDto);
        return responseService.getSingleResult(responseDto);
    }

    @GetMapping("/user/{userId}")
    public SingleResult<UserGetResponseDto> findMyReview(@PathVariable("userId") UUID userId) {
        return responseService.getSingleResult(signService.getUserUser(userId));
    }

    @PostMapping("/reissue")
    public SingleResult<TokenResponseDto> reissue(@Valid @RequestBody TokenRequestDto tokenRequestDto) {
        TokenResponseDto responseDto = signService.reIssue(tokenRequestDto);
        return responseService.getSingleResult(responseDto);
    }


}
