package com.tushar.projects.prompt_forge.service;

import com.tushar.projects.prompt_forge.dto.project.ProjectRequestDTO;
import com.tushar.projects.prompt_forge.dto.project.ProjectResponseDTO;
import com.tushar.projects.prompt_forge.dto.project.ProjectSummaryResponseDTO;

import java.util.List;

public interface ProjectService {
    List<ProjectSummaryResponseDTO> getUserProjects(Long userId);

    ProjectResponseDTO getUserProjectById(Long id, Long userId);

    ProjectResponseDTO createProject(ProjectRequestDTO projectRequest, Long userId);

    ProjectResponseDTO updateProject(Long id, ProjectRequestDTO projectRequestDTO, Long userId);

    void deleteProject(Long id, Long userId);
}
