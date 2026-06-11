package com.tushar.projects.prompt_forge.service;

import com.tushar.projects.prompt_forge.dto.subscription.CheckoutRequest;
import com.tushar.projects.prompt_forge.dto.subscription.CheckoutResponse;
import com.tushar.projects.prompt_forge.dto.subscription.PortalResponse;
import com.tushar.projects.prompt_forge.dto.subscription.SubscriptionResponse;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription(Long userID);

    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId);

    PortalResponse openCustomerPortal(Long userId);
}
