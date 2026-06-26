package com.tushar.projects.prompt_forge.service.impl;

import com.tushar.projects.prompt_forge.service.AiGenerationService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AiGenerationServiceImpl implements AiGenerationService {
    @Override
    public Flux<String> streamResponse(String message, Long aLong) {
        return null;
    }
}
