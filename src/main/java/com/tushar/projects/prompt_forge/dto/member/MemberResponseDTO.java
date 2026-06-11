package com.tushar.projects.prompt_forge.dto.member;

import com.tushar.projects.prompt_forge.enums.ProjectRole;

import java.time.Instant;

public record MemberResponseDTO(
        Long userId,
        String username,
        String name,
        String avatarUrl,
        ProjectRole projectRole,
        Instant invitedAt
) {

}
