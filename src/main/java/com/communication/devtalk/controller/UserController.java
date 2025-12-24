package com.communication.devtalk.controller;

import com.communication.devtalk.dto.UserRequest;
import com.communication.devtalk.dto.UserResponse;
import com.communication.devtalk.model.User;
import com.communication.devtalk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        User user = userService.registerUser(request.username(), request.email(), request.settings());
        return new ResponseEntity<>(mapToResponse(user), HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> getUsersByTheme(@RequestParam String theme) {
        List<User> users = userService.findUsersByTheme(theme);

        List<UserResponse> response = users.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPostCount(),
                user.getSetting()
        );
    }
}
