package com.lgsoftworks.infrastructure.rest.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        Integer statusCode,
        String errorMessage,
        LocalDateTime timestamp
) {
    public ErrorResponse(Integer statusCode, String errorMessage) {
        this(statusCode, errorMessage, LocalDateTime.now());
    }
}
