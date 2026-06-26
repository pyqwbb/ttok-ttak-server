package com.ttokttak.service;

import com.ttokttak.dao.UserDao;
import com.ttokttak.domain.User;
import com.ttokttak.dto.UpdateUserRequest;
import com.ttokttak.util.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public User getUser(String uid) {
        return userDao.findById(uid);
    }

    @Override
    public void updateUser(String uid, UpdateUserRequest req) {
        // 유효성 검사
        UserValidator.validateEmail(req.getEmail());
        UserValidator.validateNickname(req.getNickname());
        UserValidator.validateProfileImg(req.getProfileImg());

        // 중복 체크 (본인 제외)
        if (userDao.existsByEmailExcludeMe(req.getEmail(), uid)) {
            throw new IllegalArgumentException("이미 사용 중인 이메일이에요.");
        }
        if (userDao.existsByNicknameExcludeMe(req.getNickname(), uid)) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임이에요.");
        }

        User user = new User();
        user.setId(uid);
        user.setNickname(req.getNickname());
        user.setEmail(req.getEmail());
        user.setProfileImg(
                req.getProfileImg() != null ? req.getProfileImg() : "default_1.svg"
        );
        userDao.update(user);
    }
}
