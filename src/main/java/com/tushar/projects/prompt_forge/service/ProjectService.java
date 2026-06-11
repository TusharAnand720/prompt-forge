package com.tushar.projects.prompt_forge.service;

import com.tushar.projects.prompt_forge.dto.project.ProjectRequest;
import com.tushar.projects.prompt_forge.dto.project.ProjectResponse;
import com.tushar.projects.prompt_forge.dto.project.ProjectSummaryResponse;

import java.util.List;

public interface ProjectService {
    List<ProjectSummaryResponse> getUserProjects(Long userId);

    ProjectResponse getUserProjectById(Long id, Long userId);

    ProjectResponse createProject(ProjectRequest projectRequest, Long userId);

    ProjectResponse updateProject(Long id, ProjectRequest projectRequest, Long userId);

    void deleteProject(Long id, Long userId);
}
