package com.tushar.projects.prompt_forge.dto.subscription;

public record PlanResponseDTO(
        Long id,
        String name,
        Integer maxProjects,
        Integer maxTokensPerDay,
        Boolean unlimitedAi,
        String price) {
}
