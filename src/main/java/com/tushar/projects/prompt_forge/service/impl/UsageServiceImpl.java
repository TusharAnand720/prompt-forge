package com.tushar.projects.prompt_forge.service.impl;

import com.tushar.projects.prompt_forge.dto.subscription.PlanLimitResponseDTO;
import com.tushar.projects.prompt_forge.dto.subscription.UsageTodayResponseDTO;
import com.tushar.projects.prompt_forge.service.UsageService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {
    @Override
    public UsageTodayResponseDTO getTodayUsage(Long userId) {
        return null;
    }

    @Override
    public PlanLimitResponseDTO getCurrentSubscriptionLimitsOfUser(Long userId) {
        return null;
    }
}
