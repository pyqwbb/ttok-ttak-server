package com.ttokttak.service;

import com.ttokttak.dao.UserDao;
import com.ttokttak.domain.User;
import com.ttokttak.dto.SignupRequest;
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

        // 필수값 체크
        if (req.getEmail() == null || req.getEmail().isBlank()) {
            throw new IllegalArgumentException("이메일을 입력해주세요.");
        }
        if (req.getPassword() == null || req.getPassword().isBlank()) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요.");
        }
        if (req.getNickname() == null || req.getNickname().isBlank()) {
            throw new IllegalArgumentException("닉네임을 입력해주세요.");
        }

        // 이메일 형식
        if (!req.getEmail().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new IllegalArgumentException("올바른 이메일 형식이 아니에요.");
        }

        // 닉네임 2~10글자
        if (req.getNickname().length() < 2 || req.getNickname().length() > 10) {
            throw new IllegalArgumentException("닉네임은 2~10글자로 입력해주세요.");
        }

        // 비밀번호 6글자 이상
        if (req.getPassword().length() < 6) {
            throw new IllegalArgumentException("비밀번호는 최소 6글자 이상이어야 합니다.");
        }

        // 이메일 중복 체크
        if (userDao.findByEmail(req.getEmail()) != null) {
            throw new IllegalArgumentException("이미 사용 중인 이메일이에요.");
        }

        // 닉네임 중복 체크
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
