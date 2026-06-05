package com.tushar.projects.prompt_forge.service;

import com.tushar.projects.prompt_forge.dto.subscription.PlanResponseDTO;

import java.util.List;

public interface PlanService {
    List<PlanResponseDTO> getAllActivePlans();
}
