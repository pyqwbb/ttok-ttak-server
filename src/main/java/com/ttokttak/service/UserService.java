package com.ttokttak.service;

import com.ttokttak.domain.User;
import com.ttokttak.dto.UpdateUserRequest;

public interface UserService {
    User getUser(String uid);
    void updateUser(String uid, UpdateUserRequest req);
}
