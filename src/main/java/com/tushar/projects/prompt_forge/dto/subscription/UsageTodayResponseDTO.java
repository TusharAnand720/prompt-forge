package com.tushar.projects.prompt_forge.dto.subscription;

public record UsageTodayResponseDTO(
        Integer tokenUsed,
        Integer tokenLimit,
        Integer previewsRunning,
        Integer previewsLimit) {
}
