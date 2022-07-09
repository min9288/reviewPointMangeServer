package com.tripleCMS.tripleCMS.config.jwt;

import com.tripleCMS.tripleCMS.exception.UserNotFoundException;
import com.tripleCMS.tripleCMS.model.User;
import com.tripleCMS.tripleCMS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TripleUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);

        return TripleUserDetails.builder()
                .userId(user.getUserId())
                .userPw(user.getUserPw())
                .authorities(user.getRoles().stream()
                        .map(auth -> new SimpleGrantedAuthority(auth.toString())).collect(Collectors.toList()))
                .build();
    }

}
