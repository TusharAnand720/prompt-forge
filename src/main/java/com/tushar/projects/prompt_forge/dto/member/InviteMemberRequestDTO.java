package com.tushar.projects.prompt_forge.dto.member;

import com.tushar.projects.prompt_forge.enums.ProjectRole;

public record InviteMemberRequestDTO(String email, ProjectRole role) {
}
