package com.tushar.projects.prompt_forge.service.impl;


import com.tushar.projects.prompt_forge.dto.subscription.PlanResponseDTO;
import com.tushar.projects.prompt_forge.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Override
    public List<PlanResponseDTO> getAllActivePlans() {
        return List.of();
    }
}
