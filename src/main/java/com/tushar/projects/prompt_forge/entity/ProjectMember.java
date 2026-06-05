package com.tushar.projects.prompt_forge.entity;

import com.tushar.projects.prompt_forge.enums.ProjectRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectMember {

    ProjectMemberId projectMemberId;
    Project project;
    User user;

    ProjectRole projectRole;

    User invitedBy;
    Instant invitedAt;
    Instant acceptedAt;
    
}
