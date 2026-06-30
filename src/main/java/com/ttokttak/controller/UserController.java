package com.ttokttak.controller;

import com.ttokttak.domain.User;
import com.ttokttak.dto.UpdateUserRequest;
import com.ttokttak.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // GET /api/user
    @GetMapping
    public ResponseEntity<User> getUser(HttpServletRequest request) {
        String uid = (String) request.getAttribute("uid");
        return ResponseEntity.ok(userService.getUser(uid));
    }

    // PATCH /api/user
    @PatchMapping
    public ResponseEntity<?> updateUser(
            HttpServletRequest request,
            @RequestBody UpdateUserRequest req
    ) {
        try {
            String uid = (String) request.getAttribute("uid");
            userService.updateUser(uid, req);
            return ResponseEntity.ok(userService.getUser(uid));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }
}
