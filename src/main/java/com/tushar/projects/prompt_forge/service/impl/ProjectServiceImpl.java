package com.tushar.projects.prompt_forge.service.impl;

import com.tushar.projects.prompt_forge.dto.project.ProjectRequestDTO;
import com.tushar.projects.prompt_forge.dto.project.ProjectResponseDTO;
import com.tushar.projects.prompt_forge.dto.project.ProjectSummaryResponseDTO;
import com.tushar.projects.prompt_forge.entity.Project;
import com.tushar.projects.prompt_forge.entity.User;
import com.tushar.projects.prompt_forge.error.ResourceNotFoundException;
import com.tushar.projects.prompt_forge.mapper.ProjectMapper;
import com.tushar.projects.prompt_forge.reposityory.ProjectRepository;
import com.tushar.projects.prompt_forge.reposityory.UserRepository;
import com.tushar.projects.prompt_forge.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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

    ProjectMapper projectMapper;

    @Override
    public List<ProjectSummaryResponseDTO> getUserProjects(Long userId) {
        return projectRepository.findAllAccessibleByUser(userId).stream()
                .map(projectMapper::toProjectSummaryResponseDTO)
                .toList();
    }

    @Override
    public ProjectResponseDTO getUserProjectById(Long id, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        return projectMapper.toProjectResponseDTO(project);
    }

    @Override
    public ProjectResponseDTO createProject(ProjectRequestDTO projectRequest, Long userId) {

        User owner = userRepository.findById(userId).orElseThrow();

        Project project = Project.builder()
                .name(projectRequest.name())
                .owner(owner)
                .build();

        projectRepository.save(project);

        return projectMapper.toProjectResponseDTO(project);
    }

    @Override
    public ProjectResponseDTO updateProject(Long id, ProjectRequestDTO projectRequestDTO, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        project.setName(projectRequestDTO.name());
        project = projectRepository.save(project);
        return projectMapper.toProjectResponseDTO(project);
    }

    @Override
    public void deleteProject(Long id, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    // === Internal Functions ===

    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("projectId-userId", projectId + "-" + userId));
    }
}
