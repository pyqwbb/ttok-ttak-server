package com.ttokttak.service;

import com.ttokttak.dao.UserDao;
import com.ttokttak.domain.User;
import com.ttokttak.dto.SignupRequest;
import com.ttokttak.util.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserDao userDao;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void signup(SignupRequest req) {

        // 유효성 검사
        UserValidator.validateEmail(req.getEmail());
        UserValidator.validatePassword(req.getPassword());
        UserValidator.validateNickname(req.getNickname());

        // 중복 체크
        if (userDao.findByEmail(req.getEmail()) != null) {
            throw new IllegalArgumentException("이미 사용 중인 이메일이에요.");
        }
        if (userDao.existsByNickname(req.getNickname())) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임이에요.");
        }

        User user = new User();
        user.setId(UUID.randomUUID().toString().substring(0, 8)); // 8자리 고유 ID 생성
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setNickname(req.getNickname());
        user.setProfileImg("default_1.svg");
        user.setProvider("local");

        userDao.insert(user);
    }
}
