package com.tushar.projects.prompt_forge.error;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

@Builder
public record ApiError(
        HttpStatus status,
        String message,
        List<String> subErrors,
        Instant timestamp) {

    public ApiError(HttpStatus status, String message) {
        this(status, message, null, Instant.now());
    }

    public ApiError(HttpStatus status, List<String> subErrors, String message) {
        this(status, message, subErrors, Instant.now());
    }
}
