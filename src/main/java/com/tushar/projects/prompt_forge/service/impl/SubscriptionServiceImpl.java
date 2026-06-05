package com.tushar.projects.prompt_forge.service.impl;

import com.tushar.projects.prompt_forge.dto.subscription.CheckoutRequestDTO;
import com.tushar.projects.prompt_forge.dto.subscription.CheckoutResponseDTO;
import com.tushar.projects.prompt_forge.dto.subscription.PortalResponseDTO;
import com.tushar.projects.prompt_forge.dto.subscription.SubscriptionResponseDTO;
import com.tushar.projects.prompt_forge.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Override
    public SubscriptionResponseDTO getCurrentSubscription(Long userID) {
        return null;
    }

    @Override
    public CheckoutResponseDTO createCheckoutSessionUrl(CheckoutRequestDTO request, Long userId) {
        return null;
    }

    @Override
    public PortalResponseDTO openCustomerPortal(Long userId) {
        return null;
    }
}
