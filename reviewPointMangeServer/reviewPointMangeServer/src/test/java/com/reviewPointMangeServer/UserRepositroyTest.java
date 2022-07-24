package com.reviewPointMangeServer;

import com.reviewPointMangeServer.domain.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
public class UserRepositroyTest {

    @Autowired
    UserRepository userRepository;


}
