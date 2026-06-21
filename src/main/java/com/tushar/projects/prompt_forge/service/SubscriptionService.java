package com.tushar.projects.prompt_forge.service;

import com.tushar.projects.prompt_forge.dto.subscription.SubscriptionResponse;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription(Long userID);

}
