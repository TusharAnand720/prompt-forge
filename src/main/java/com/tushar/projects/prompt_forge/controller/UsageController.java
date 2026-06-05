package com.tushar.projects.prompt_forge.controller;

import com.tushar.projects.prompt_forge.dto.subscription.PlanLimitResponseDTO;
import com.tushar.projects.prompt_forge.dto.subscription.UsageTodayResponseDTO;
import com.tushar.projects.prompt_forge.service.UsageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/usage")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UsageController {

    UsageService usageService;

    @GetMapping("/today")
    public ResponseEntity<UsageTodayResponseDTO> getTodayUsage() {
        Long userId = 0L;
        return ResponseEntity.ok(usageService.getTodayUsage(userId));
    }

    @GetMapping("/limits")
    public ResponseEntity<PlanLimitResponseDTO> getPlanLimits() {
        Long userId = 0L;
        return ResponseEntity.ok(usageService.getCurrentSubscriptionLimitsOfUser(userId));
    }
}
