package com.tripleCMS.tripleCMS;

import com.tripleCMS.tripleCMS.model.User;
import com.tripleCMS.tripleCMS.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
@Transactional
@Rollback(false)
public class UserRepositroyTest {

    @Autowired
    UserRepository userRepository;

//    @Test
//    @DisplayName("유저 아이디와 패스워드로 가입")
//    void joinTest() {
//
//        Date dt = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String datetime = sdf.format(dt);
//        String userId = "minu";
//        String password = "qwer1234";
//        UUID uuid = UUID.randomUUID();
//
//        User user = User.builder()
//                .userUUID(uuid)
//                .userId(userId)
//                .userPw(password)
//                .point(0)
//                .userEnrollDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
//                .build();
//        userRepository.save(user);
//    }
}
