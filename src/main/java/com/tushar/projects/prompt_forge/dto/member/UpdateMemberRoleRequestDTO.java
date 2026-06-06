package com.tushar.projects.prompt_forge.dto.member;

import com.tushar.projects.prompt_forge.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequestDTO(
        @NotNull ProjectRole role) {
}
