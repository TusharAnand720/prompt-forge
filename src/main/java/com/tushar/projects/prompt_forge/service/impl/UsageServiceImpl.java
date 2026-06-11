package com.tushar.projects.prompt_forge.service.impl;

import com.tushar.projects.prompt_forge.dto.subscription.PlanLimitResponse;
import com.tushar.projects.prompt_forge.dto.subscription.UsageTodayResponse;
import com.tushar.projects.prompt_forge.service.UsageService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {
    @Override
    public UsageTodayResponse getTodayUsage(Long userId) {
        return null;
    }

    @Override
    public PlanLimitResponse getCurrentSubscriptionLimitsOfUser(Long userId) {
        return null;
    }
}
