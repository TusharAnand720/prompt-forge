package com.tushar.projects.prompt_forge.service;

import com.tushar.projects.prompt_forge.dto.subscription.PlanResponse;

import java.util.List;

public interface PlanService {
    List<PlanResponse> getAllActivePlans();
}
