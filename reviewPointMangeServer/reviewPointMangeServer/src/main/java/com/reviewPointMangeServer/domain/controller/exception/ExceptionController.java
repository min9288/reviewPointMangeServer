package com.reviewPointMangeServer.domain.controller.exception;

import com.reviewPointMangeServer.exception.AuthenticationEntryPointException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping(value = "/entry")
    public void EntryPointException() {
        throw new AuthenticationEntryPointException();
    }

    @GetMapping(value = "/denied")
    public void AccessDeniedException() {
        throw new AccessDeniedException("");
    }
}
