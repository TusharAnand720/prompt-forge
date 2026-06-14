package com.tushar.projects.prompt_forge.security;

import com.tushar.projects.prompt_forge.enums.ProjectRole;
import com.tushar.projects.prompt_forge.reposityory.ProjectMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("security")
@RequiredArgsConstructor
public class SecurityExpression {

    private final ProjectMemberRepository projectMemberRepository;
    private final AuthUtil authUtil;

    public boolean canViewProject(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        return projectMemberRepository.findRoleByProjectIdAndUserId(projectId, userId)
                .map(role -> role.equals(ProjectRole.VIEWER) || role.equals(ProjectRole.EDITOR) || role.equals(ProjectRole.OWNER))
                .orElse(false);
    }

    public boolean canEditProject(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        return projectMemberRepository.findRoleByProjectIdAndUserId(projectId, userId)
                .map(role -> role.equals(ProjectRole.EDITOR) || role.equals(ProjectRole.OWNER))
                .orElse(false);
    }
}
