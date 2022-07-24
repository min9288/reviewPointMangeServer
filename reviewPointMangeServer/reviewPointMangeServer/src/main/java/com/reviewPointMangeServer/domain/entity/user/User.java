package com.reviewPointMangeServer.domain.entity.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.reviewPointMangeServer.domain.entity.enumPackage.Role;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user")
public class User{

    // 회원 UUID
    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    // 회원 아이디
    @Column(nullable = false, unique = true, length = 40)
    private String userName;

    // 회원 패스워드
    @Column(nullable = false, length = 100)
    private String userPw;

    // 회원 보유 포인트
    @ColumnDefault("0")
    private int point;

    // 회원레벨
    @ColumnDefault("0")
    private int userLevel;

    // 회원 가입일
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @CreationTimestamp
    private Timestamp userEnrollDate;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private List<Role> roles = new ArrayList<>();

    @Builder
    public User(String userName, String userPw, List<Role> roles) {
        this.userName = userName;
        this.userPw = userPw;
        this.roles = Collections.singletonList(Role.ROLE_MEMBER);
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

//    @Transient
    private String refreshToken;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Builder
    public User(UUID userId, int point, int userLevel) {
        this.userId = userId;
        this.point = point;
        this.userLevel = userLevel;
    }

}
