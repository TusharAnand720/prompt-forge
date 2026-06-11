package com.tushar.projects.prompt_forge.service.impl;

import com.tushar.projects.prompt_forge.dto.subscription.CheckoutRequest;
import com.tushar.projects.prompt_forge.dto.subscription.CheckoutResponse;
import com.tushar.projects.prompt_forge.dto.subscription.PortalResponse;
import com.tushar.projects.prompt_forge.dto.subscription.SubscriptionResponse;
import com.tushar.projects.prompt_forge.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Override
    public SubscriptionResponse getCurrentSubscription(Long userID) {
        return null;
    }

    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId) {
        return null;
    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }
}
