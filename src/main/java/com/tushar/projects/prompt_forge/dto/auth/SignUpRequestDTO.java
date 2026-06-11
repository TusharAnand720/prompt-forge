package com.tushar.projects.prompt_forge.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequestDTO(
        @Email
        @NotBlank
        String username,

        @Size(min = 1, max = 30)
        String name,

        @Size(min = 4, max = 50)
        String password) {
}
