package com.tushar.projects.prompt_forge.dto.subscription;

public record PlanLimitResponseDTO(
        String planName,
        Integer maxTokensPerDay,
        Integer maxProjects,
        Boolean unlimitedAi) {
}
