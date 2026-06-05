package com.tushar.projects.prompt_forge.dto.auth;

public record UserProfileResponseDTO(
        Long id,
        String email,
        String name,
        String avatarUrl) {
}
