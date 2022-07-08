package com.tripleCMS.tripleCMS.repository;

import com.tripleCMS.tripleCMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Date;

public interface UserRepository extends JpaRepository<User, Object> {
    Optional<User> findByUserIdAndUserPw(String userId, String UserPw);
}
