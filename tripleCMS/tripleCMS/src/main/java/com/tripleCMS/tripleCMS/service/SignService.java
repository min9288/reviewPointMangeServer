package com.tripleCMS.tripleCMS.service;

import com.tripleCMS.tripleCMS.config.jwt.JwtTokenProvider;
import com.tripleCMS.tripleCMS.dto.requestDto.jwt.TokenRequestDto;
import com.tripleCMS.tripleCMS.dto.requestDto.user.UserLoginRequestDto;
import com.tripleCMS.tripleCMS.dto.requestDto.user.UserRegisterRequestDto;
import com.tripleCMS.tripleCMS.dto.responseDto.jwt.TokenResponseDto;
import com.tripleCMS.tripleCMS.dto.responseDto.user.UserLoginResponseDto;
import com.tripleCMS.tripleCMS.dto.responseDto.user.UserRegisterResponseDto;
import com.tripleCMS.tripleCMS.exception.InvalidRefreshTokenException;
import com.tripleCMS.tripleCMS.exception.LoginFailureException;
import com.tripleCMS.tripleCMS.exception.UserIdAlreadyExistsException;
import com.tripleCMS.tripleCMS.exception.UserNotFoundException;
import com.tripleCMS.tripleCMS.model.User;
import com.tripleCMS.tripleCMS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class SignService {

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    // DTO로 들어온 값을 통해 회원가입을 진행
    @Transactional
    public UserRegisterResponseDto registerUser(UserRegisterRequestDto requestDto) {
        validateDuplicated(requestDto.getUserId());
        User user = userRepository.save(
                User.builder()
                        .userId(requestDto.getUserId())
                        .userPw(passwordEncoder.encode(requestDto.getUserPw()))
                        .build());
        return UserRegisterResponseDto.builder()
                .userId(user.getUserId())
                .userPw(user.getUserPw())
                .build();
    }

    public void validateDuplicated(String userId) {
        if (userRepository.findByUserId(userId).isPresent()) throw new UserIdAlreadyExistsException();
    }

    // 로그인 구현
    @Transactional
    public UserLoginResponseDto loginUser(UserLoginRequestDto requestDto) {
        User user = userRepository.findByUserId(requestDto.getUserId()).orElseThrow(UserNotFoundException::new);
        if(!passwordEncoder.matches(requestDto.getUserPw(), user.getUserPw()))
            throw new LoginFailureException();
        user.updateRefreshToken(jwtTokenProvider.createRefreshToken());
        return new UserLoginResponseDto(user.getUserUUID(), jwtTokenProvider.createToken(requestDto.getUserId()), user.getRefreshToken());
    }

    // 토큰 재발행
    @Transactional
    public TokenResponseDto reIssue(TokenRequestDto requestDto) {
        if(!jwtTokenProvider.validateTokenExpiration(requestDto.getRefreshToken()))
            throw new InvalidRefreshTokenException();
        User user = findUserByToken(requestDto);

        if(!user.getRefreshToken().equals(requestDto.getRefreshToken()))
            throw new InvalidRefreshTokenException();
        String accessToken = jwtTokenProvider.createToken(user.getUserId());
        String refreshToken = jwtTokenProvider.createRefreshToken();
        user.updateRefreshToken(refreshToken);
        return new TokenResponseDto(accessToken, refreshToken);
    }
    public User findUserByToken(TokenRequestDto requestDto) {
        Authentication auth = jwtTokenProvider.getAuthentication(requestDto.getAccessToken());
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String userId = userDetails.getUsername();
        return userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);
    }
}
