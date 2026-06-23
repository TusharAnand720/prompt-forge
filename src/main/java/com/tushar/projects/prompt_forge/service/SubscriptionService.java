package com.tushar.projects.prompt_forge.service;

import com.tushar.projects.prompt_forge.dto.subscription.SubscriptionResponse;
import com.tushar.projects.prompt_forge.enums.SubscriptionStatus;

import java.time.Instant;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription();

    void activateSubscription(Long userId, Long planId, String subscriptionId, String customerId);

    void updateSubscription(String subscriptionId, SubscriptionStatus status, Instant periodStart, Instant periodEnd, Boolean cancelAtPeriodEnd, Long planId);

    void cancelSubscription(String subscriptionId);

    void renewSubscriptionPeriod(String subscriptionId, Instant periodStart, Instant periodEnd);

    void markSubscriptionPastDue(String subscriptionId);
}
