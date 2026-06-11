package com.tushar.projects.prompt_forge.service.impl;


import com.tushar.projects.prompt_forge.dto.subscription.PlanResponse;
import com.tushar.projects.prompt_forge.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Override
    public List<PlanResponse> getAllActivePlans() {
        return List.of();
    }
}
