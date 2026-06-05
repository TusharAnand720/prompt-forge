package com.tushar.projects.prompt_forge.dto.auth;

public record SignUpRequestDTO(
        String email,
        String name,
        String password) {
}
