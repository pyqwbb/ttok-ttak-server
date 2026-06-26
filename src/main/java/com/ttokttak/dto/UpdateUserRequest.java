package com.ttokttak.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String nickname;
    private String email;
    private String profileImg;
}
