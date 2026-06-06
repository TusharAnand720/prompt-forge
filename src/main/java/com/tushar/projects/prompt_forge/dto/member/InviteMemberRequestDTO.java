package com.tushar.projects.prompt_forge.dto.member;

import com.tushar.projects.prompt_forge.enums.ProjectRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InviteMemberRequestDTO(
        @Email @NotBlank String email,
        @NotNull ProjectRole role) {
}
