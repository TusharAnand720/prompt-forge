package com.tushar.projects.prompt_forge.entity;

import com.tushar.projects.prompt_forge.enums.SubscriptionStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subscription {

    Long id;

    User user;
    Plan plan;

    SubscriptionStatus status;

    String stripeSubscriptionId;

    Instant currentPeriodStart; // Current plan was started ar
    Instant currentPeriodEnd; // Current plan is going to end at
    Boolean cancelAtPeriodEnd; // is plan going to cancel at currentPeriodEnd

    Instant createdAt;
    Instant updatedAt;

}
