package com.reviewPointMangeServer.reviewPointMangeServer.controller;

import com.reviewPointMangeServer.reviewPointMangeServer.dto.responseDto.user.UserGetResponseDto;
import com.reviewPointMangeServer.reviewPointMangeServer.dto.requestDto.jwt.TokenRequestDto;
import com.reviewPointMangeServer.reviewPointMangeServer.dto.requestDto.user.UserLoginRequestDto;
import com.reviewPointMangeServer.reviewPointMangeServer.dto.requestDto.user.UserRegisterRequestDto;
import com.reviewPointMangeServer.reviewPointMangeServer.dto.responseDto.jwt.TokenResponseDto;
import com.reviewPointMangeServer.reviewPointMangeServer.dto.responseDto.user.UserLoginResponseDto;
import com.reviewPointMangeServer.reviewPointMangeServer.dto.responseDto.user.UserRegisterResponseDto;
import com.reviewPointMangeServer.reviewPointMangeServer.service.ResponseService;
import com.reviewPointMangeServer.reviewPointMangeServer.service.SignService;
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
