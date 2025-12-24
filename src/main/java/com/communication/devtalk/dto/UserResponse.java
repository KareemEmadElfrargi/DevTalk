package com.communication.devtalk.dto;

import java.util.Map;

public record UserResponse(
        Long id,
        String username,
        String email,
        Integer postCount,
        Map<String, Object> settings
) {}
