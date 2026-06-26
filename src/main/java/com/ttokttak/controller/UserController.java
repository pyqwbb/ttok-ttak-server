package com.ttokttak.controller;

import com.ttokttak.domain.User;
import com.ttokttak.dto.UpdateUserRequest;
import com.ttokttak.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 임시 고정 uid — JWT 구현 후 교체 예정
    private static final String TEMP_UID = "a63ec1eb";

    // GET /api/user
    @GetMapping
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok(userService.getUser(TEMP_UID));
    }

    // PATCH /api/user
    @PatchMapping
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest req) {
        try {
            userService.updateUser(TEMP_UID, req);
            return ResponseEntity.ok(userService.getUser(TEMP_UID));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }
}
