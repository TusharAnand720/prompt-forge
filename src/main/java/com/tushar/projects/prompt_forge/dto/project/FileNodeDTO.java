package com.tushar.projects.prompt_forge.dto.project;

import java.time.Instant;

public record FileNodeDTO(
        String path,
        Instant modifiedAt,
        Long size,
        String type
) {
}
