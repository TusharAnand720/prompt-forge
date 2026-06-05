package com.tushar.projects.prompt_forge.controller;

import com.tushar.projects.prompt_forge.dto.subscription.*;
import com.tushar.projects.prompt_forge.service.PlanService;
import com.tushar.projects.prompt_forge.service.SubscriptionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BillingController {

    PlanService planService;
    SubscriptionService subscriptionService;

    @GetMapping("/api/plans")
    public ResponseEntity<List<PlanResponseDTO>> getAllPlans() {
        return ResponseEntity.ok(planService.getAllActivePlans());
    }

    @GetMapping("api/me/subscription")
    public ResponseEntity<SubscriptionResponseDTO> getMySubscription() {
        Long userID = 0L;
        return ResponseEntity.ok(subscriptionService.getCurrentSubscription(userID));
    }

    @PostMapping("/api/stripe/checkout")
    public ResponseEntity<CheckoutResponseDTO> createCheckoutResponse(@RequestBody CheckoutRequestDTO request) {
        Long userId = 0L;
        return ResponseEntity.ok(subscriptionService.createCheckoutSessionUrl(request, userId));
    }

    @PostMapping("/api/stripe/portal")
    public ResponseEntity<PortalResponseDTO> openCustomerPortal() {
        Long userId = 0L;
        return ResponseEntity.ok(subscriptionService.openCustomerPortal(userId));
    }


}
