package com.tushar.projects.prompt_forge.dto.subscription;

import java.time.Instant;

public record SubscriptionResponse(
        PlanResponse plan,
        String status,
        Instant currentPeriodEnd,
        Long tokenUsedThisCycle) {
}
