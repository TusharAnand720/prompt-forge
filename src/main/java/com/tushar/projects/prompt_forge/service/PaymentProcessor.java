package com.tushar.projects.prompt_forge.service;

import com.stripe.model.StripeObject;
import com.tushar.projects.prompt_forge.dto.subscription.CheckoutRequest;
import com.tushar.projects.prompt_forge.dto.subscription.CheckoutResponse;
import com.tushar.projects.prompt_forge.dto.subscription.PortalResponse;

import java.util.Map;

public interface PaymentProcessor {
    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request);

    PortalResponse openCustomerPortal(Long userId);

    void handleWebhookEvent(String type, StripeObject stripeObject, Map<String, String> metadata);
}
