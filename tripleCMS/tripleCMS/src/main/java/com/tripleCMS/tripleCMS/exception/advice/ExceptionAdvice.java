package com.tripleCMS.tripleCMS.exception.advice;

import com.tripleCMS.tripleCMS.exception.InvalidRefreshTokenException;
import com.tripleCMS.tripleCMS.exception.LoginFailureException;
import com.tripleCMS.tripleCMS.exception.UserIdAlreadyExistsException;
import com.tripleCMS.tripleCMS.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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


}
