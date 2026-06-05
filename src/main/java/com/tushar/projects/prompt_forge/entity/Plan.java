package com.tushar.projects.prompt_forge.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Plan {

    Long id;

    String name;
    String stripePriceId;
    Integer maxProjects; // max number of project allowed to be created and accessed
    Integer maxTokenPerDay; // max number of token allowed to use per day
    Integer maxPreviews; // max number of preview allowed as per plan
    Boolean unlimitedAi; // unlimited token for LL is true and ignore maxTokenPerDay

    Boolean isActive;

}
