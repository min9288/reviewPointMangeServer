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


}
