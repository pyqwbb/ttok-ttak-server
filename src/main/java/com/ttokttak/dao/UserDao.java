package com.ttokttak.dao;

import com.ttokttak.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    // 이메일로 유저 조회 (중복 체크용)
    User findByEmail(@Param("email") String email);

    // 닉네임 중복 체크
    boolean existsByNickname(@Param("nickname") String nickname);

    // 회원 등록
    int insert(User user);
}
