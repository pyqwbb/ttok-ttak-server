package com.ttokttak.dao;

import com.ttokttak.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    // ID로 회원 정보 조회
    User findById(@Param("id") String id);

    // 이메일로 유저 조회 (중복 체크용)
    User findByEmail(@Param("email") String email);

    // 닉네임 중복 체크
    boolean existsByNickname(@Param("nickname") String nickname);

    // 본인 제외 이메일 중복 체크
    boolean existsByEmailExcludeMe(@Param("email") String email, @Param("id") String id);

    // 본인 제외 닉네임 중복 체크
    boolean existsByNicknameExcludeMe(@Param("nickname") String nickname, @Param("id") String id);

    // 회원 등록
    int insert(User user);

    // 회원 정보 수정
    int update(User user);
}
