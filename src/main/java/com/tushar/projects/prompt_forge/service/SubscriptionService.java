package com.tushar.projects.prompt_forge.service;

import com.tushar.projects.prompt_forge.dto.subscription.CheckoutRequestDTO;
import com.tushar.projects.prompt_forge.dto.subscription.CheckoutResponseDTO;
import com.tushar.projects.prompt_forge.dto.subscription.PortalResponseDTO;
import com.tushar.projects.prompt_forge.dto.subscription.SubscriptionResponseDTO;

public interface SubscriptionService {
    SubscriptionResponseDTO getCurrentSubscription(Long userID);

    CheckoutResponseDTO createCheckoutSessionUrl(CheckoutRequestDTO request, Long userId);

    PortalResponseDTO openCustomerPortal(Long userId);
}
