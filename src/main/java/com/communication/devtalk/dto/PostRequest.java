package com.communication.devtalk.dto;

public record PostRequest(
        Long userId,
        String content
) {}
