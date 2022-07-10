package com.tripleCMS.tripleCMS.controller;

import com.tripleCMS.tripleCMS.dto.requestDto.jwt.TokenRequestDto;
import com.tripleCMS.tripleCMS.dto.requestDto.user.UserLoginRequestDto;
import com.tripleCMS.tripleCMS.dto.requestDto.user.UserRegisterRequestDto;
import com.tripleCMS.tripleCMS.dto.responseDto.jwt.TokenResponseDto;
import com.tripleCMS.tripleCMS.dto.responseDto.user.UserLoginResponseDto;
import com.tripleCMS.tripleCMS.dto.responseDto.user.UserRegisterResponseDto;
import com.tripleCMS.tripleCMS.service.ResponseService;
import com.tripleCMS.tripleCMS.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.SingleResult;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/sign")
public class SingController {
    private final SignService signService;
    private final ResponseService responseService;

    private final ReviewController reviewController;

    @PostMapping("/register")
    public SingleResult<UserRegisterResponseDto> register(@RequestBody UserRegisterRequestDto requestDto) {
        UserRegisterResponseDto responseDto = signService.registerUser(requestDto);
        return responseService.getSingleResult(responseDto);
    }

    @PostMapping("/login")
    public SingleResult<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto requestDto) {
        UserLoginResponseDto responseDto = signService.loginUser(requestDto);
        return responseService.getSingleResult(responseDto);
    }

//    @PostMapping("/login")
//    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto requestDto) {
//        UserLoginResponseDto responseDto = signService.loginUser(requestDto);
//        return reviewController.test(responseDto);
//    }

    @PostMapping("/reissue")
    public SingleResult<TokenResponseDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        TokenResponseDto responseDto = signService.reIssue(tokenRequestDto);
        return responseService.getSingleResult(responseDto);
    }


}
