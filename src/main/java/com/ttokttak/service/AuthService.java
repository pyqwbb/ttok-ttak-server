package com.ttokttak.service;

import com.ttokttak.dto.LoginRequest;
import com.ttokttak.dto.SignupRequest;

public interface AuthService {
    void signup(SignupRequest req);
    String login(LoginRequest req);
}
