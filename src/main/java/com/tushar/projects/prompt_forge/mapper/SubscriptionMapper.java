package com.tushar.projects.prompt_forge.mapper;

import com.tushar.projects.prompt_forge.dto.subscription.PlanResponse;
import com.tushar.projects.prompt_forge.dto.subscription.SubscriptionResponse;
import com.tushar.projects.prompt_forge.entity.Plan;
import com.tushar.projects.prompt_forge.entity.Subscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionResponse toSubscriptionResponse(Subscription subscription);

    PlanResponse toPlanResponse(Plan plan);
}
