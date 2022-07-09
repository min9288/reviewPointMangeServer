package com.tripleCMS.tripleCMS.config.jwt;

import com.tripleCMS.tripleCMS.model.User;
import com.tripleCMS.tripleCMS.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${spring.jwt.secretKey}")
    private String secretKey;

    // 토큰 유효시간 30분
    private long tokenValidTime = 30 * 60 * 1000L;
    // 토큰 만료기간 7일
    private long refreshTokenValidTime = 1000L * 60 * 60 * 24 * 7;

    private final UserDetailsService userDetailsService;
    private final TripleUserDetailsService tripleUserDetailsService;

    private UserRepository userRepository;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // 토큰 생성 로직
    public String createToken(String userId) {
        Claims claims = Jwts.claims().setSubject(userId); // 토큰 키 Subject는 중복되지 않는 고유 값 userId로 지정
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // 만료시간은 지금 시간부터 30분
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String createRefreshToken() {
        Date now = new Date();

        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserId(token));
        TripleUserDetails tripleUserDetails = (TripleUserDetails) tripleUserDetailsService.loadUserByUsername(getUserId(token));
        return new UsernamePasswordAuthenticationToken(tripleUserDetails, "", tripleUserDetails.getAuthorities());
    }

    public String getUserId(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        } catch(ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }

    public String resolveToken(HttpServletRequest req) {
        return req.getHeader("X-AUTH-TOKEN");
    }

    public boolean validateTokenExpiration(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
