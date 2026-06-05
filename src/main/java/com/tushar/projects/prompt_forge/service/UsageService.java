package com.tushar.projects.prompt_forge.service;

import com.tushar.projects.prompt_forge.dto.subscription.PlanLimitResponseDTO;
import com.tushar.projects.prompt_forge.dto.subscription.UsageTodayResponseDTO;

public interface UsageService {
    UsageTodayResponseDTO getTodayUsage(Long userId);

    PlanLimitResponseDTO getCurrentSubscriptionLimitsOfUser(Long userId);
}
