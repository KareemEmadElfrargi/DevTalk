package com.communication.devtalk.dto;

import java.time.LocalDateTime;

public record PostResponse(
        Long id,
        String content,
        String authorName,
        LocalDateTime createdAt,
        int commentsCount
) {}

