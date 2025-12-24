package com.communication.devtalk.dto;

import java.util.Map;

public record UserRequest(
        String username,
        String email,
        Map<String, Object> settings
) {}

