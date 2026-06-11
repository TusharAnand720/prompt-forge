package com.tushar.projects.prompt_forge.dto.project;

import java.time.Instant;

public record ProjectResponseDTO(
        Long id,
        String name,
        Instant createdAt,
        Instant updatedAt) {
}
