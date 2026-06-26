package com.ttokttak.util;

public class UserValidator {

    public static void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("이메일을 입력해주세요.");
        }
        if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new IllegalArgumentException("올바른 이메일 형식이 아니에요.");
        }
    }

    public static void validateNickname(String nickname) {
        if (nickname == null || nickname.isBlank()) {
            throw new IllegalArgumentException("닉네임을 입력해주세요.");
        }
        if (nickname.length() < 2 || nickname.length() > 10) {
            throw new IllegalArgumentException("닉네임은 2~10글자로 입력해주세요.");
        }
    }

    public static void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요.");
        }
        if (password.length() < 6) {
            throw new IllegalArgumentException("비밀번호는 최소 6글자 이상이어야 합니다.");
        }
    }

    public static void validateProfileImg(String profileImg) {
        if (profileImg == null) return;
        java.util.List<String> allowed = java.util.List.of(
                "default_1.svg", "default_2.svg", "default_3.svg", "default_4.svg"
        );
        if (!allowed.contains(profileImg)) {
            throw new IllegalArgumentException("올바른 프로필 이미지가 아니에요.");
        }
    }
}
