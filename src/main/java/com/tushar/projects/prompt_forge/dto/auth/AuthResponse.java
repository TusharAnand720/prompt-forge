package com.tushar.projects.prompt_forge.dto.auth;

public record AuthResponse(
        String token,
        UserProfileResponse userProfileResponseDTO) {

}
