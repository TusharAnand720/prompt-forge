package com.tushar.projects.prompt_forge.service;

import com.tushar.projects.prompt_forge.dto.subscription.PlanLimitResponse;
import com.tushar.projects.prompt_forge.dto.subscription.UsageTodayResponse;

public interface UsageService {
    UsageTodayResponse getTodayUsage(Long userId);

    PlanLimitResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
