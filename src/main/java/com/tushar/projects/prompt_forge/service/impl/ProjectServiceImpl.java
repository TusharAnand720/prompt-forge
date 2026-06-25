package com.tushar.projects.prompt_forge.service.impl;

import com.tushar.projects.prompt_forge.dto.project.ProjectRequest;
import com.tushar.projects.prompt_forge.dto.project.ProjectResponse;
import com.tushar.projects.prompt_forge.dto.project.ProjectSummaryResponse;
import com.tushar.projects.prompt_forge.entity.Project;
import com.tushar.projects.prompt_forge.entity.ProjectMember;
import com.tushar.projects.prompt_forge.entity.ProjectMemberId;
import com.tushar.projects.prompt_forge.entity.User;
import com.tushar.projects.prompt_forge.enums.Role;
import com.tushar.projects.prompt_forge.error.BadRequestException;
import com.tushar.projects.prompt_forge.error.ResourceNotFoundException;
import com.tushar.projects.prompt_forge.mapper.ProjectMapper;
import com.tushar.projects.prompt_forge.reposityory.ProjectMemberRepository;
import com.tushar.projects.prompt_forge.reposityory.ProjectRepository;
import com.tushar.projects.prompt_forge.reposityory.UserRepository;
import com.tushar.projects.prompt_forge.security.AuthUtil;
import com.tushar.projects.prompt_forge.service.ProjectService;
import com.tushar.projects.prompt_forge.service.SubscriptionService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMemberRepository projectMemberRepository;

    ProjectMapper projectMapper;

    AuthUtil authUtil;

    SubscriptionService subscriptionService;

    @Override
    public List<ProjectSummaryResponse> getUserProjects() {
        Long userId = authUtil.getCurrentUserId();
        return projectRepository.findAllAccessibleByUser(userId).stream()
                .map(projectMapper::toProjectSummaryResponse)
                .toList();
    }

    @Override
    @PreAuthorize("@security.canViewProject(#projectId)")
    public ProjectResponse getUserProjectById(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse createProject(ProjectRequest projectRequest) {

        if (!subscriptionService.canCreateProject()) {
            throw new BadRequestException("User has reached the maximum number of projects allowed by their subscription plan.");
        }

        Long userId = authUtil.getCurrentUserId();

        User owner = userRepository.getReferenceById(userId);

        Project project = Project.builder()
                .name(projectRequest.name())
                .build();
        projectRepository.save(project);

        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(), owner.getId());
        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .projectRole(Role.OWNER)
                .user(owner)
                .project(project)
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .build();
        projectMemberRepository.save(projectMember);


        return projectMapper.toProjectResponse(project);
    }

    @Override
    @PreAuthorize("@security.canEditProject(#projectId)")
    public ProjectResponse updateProject(Long projectId, ProjectRequest projectRequest) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);
        project.setName(projectRequest.name());
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    @PreAuthorize("@security.canDeleteProject(#projectId)")
    public void deleteProject(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    // === Internal Functions ===

    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("projectId-userId", projectId + "-" + userId));
    }
}
