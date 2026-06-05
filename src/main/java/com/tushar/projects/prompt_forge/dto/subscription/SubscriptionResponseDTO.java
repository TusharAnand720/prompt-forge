package com.tushar.projects.prompt_forge.dto.subscription;

import java.time.Instant;

public record SubscriptionResponseDTO(
        PlanResponseDTO plan,
        String status,
        Instant periodEnd,
        Long tokenUsedThisCycle) {
}
