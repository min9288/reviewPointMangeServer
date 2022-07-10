package com.tripleCMS.tripleCMS.exception.advice;

import com.tripleCMS.tripleCMS.exception.*;
import com.tripleCMS.tripleCMS.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import result.Result;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(LoginFailureException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result loginFailureException() {
        return responseService.getFailureResult(-101, "아이디 혹은 비밀번호가 틀립니다.");
    }

    @ExceptionHandler(UserIdAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result userIdAlreadyExistsException() {
        return responseService.getFailureResult(-102, "이미 존재하는 아이디입니다.");
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result invalidRefreshTokenException() {
        return responseService.getFailureResult(-103, "Refresh Token이 유효하지 않습니다.");
    }

    @ExceptionHandler(PlaceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result placeAlreadyExistsException() {
        return responseService.getFailureResult(-104, "이미 존재하는 장소 입니다.");
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result userNotFoundException() {
        return responseService.getFailureResult(-105, "유저 정보를 찾을 수 없습니다.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result accessDeniedException() {
        return responseService.getFailureResult(-106, "인증 정보를 확인할 수 없습니다.");
    }

    @ExceptionHandler(WriterAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result writerAlreadyExistsException() {
        return responseService.getFailureResult(-107, "한 장소에서 중복으로 작성할 수 없습니다.");
    }



}
