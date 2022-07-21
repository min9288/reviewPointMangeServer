package com.reviewPointMangeServer.reviewPointMangeServer.config.jwt;

import com.reviewPointMangeServer.reviewPointMangeServer.exception.UserNotFoundException;
import com.reviewPointMangeServer.reviewPointMangeServer.user.model.User;
import com.reviewPointMangeServer.reviewPointMangeServer.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReviewUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName).orElseThrow(UserNotFoundException::new);

        return ReviewUserDetails.builder()
                .userName(user.getUserName())
                .userPw(user.getUserPw())
                .authorities(user.getRoles().stream()
                        .map(auth -> new SimpleGrantedAuthority(auth.toString())).collect(Collectors.toList()))
                .build();
    }

}
