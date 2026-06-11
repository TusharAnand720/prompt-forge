package com.tushar.projects.prompt_forge.dto.project;

import jakarta.validation.constraints.NotBlank;

public record ProjectRequest(
        @NotBlank
        String name) {
}
