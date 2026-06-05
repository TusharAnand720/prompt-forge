package com.tushar.projects.prompt_forge.mapper;

import com.tushar.projects.prompt_forge.dto.project.ProjectResponseDTO;
import com.tushar.projects.prompt_forge.dto.project.ProjectSummaryResponseDTO;
import com.tushar.projects.prompt_forge.entity.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponseDTO toProjectResponseDTO(Project project);

    ProjectSummaryResponseDTO toProjectSummaryResponseDTO(Project project);
}
